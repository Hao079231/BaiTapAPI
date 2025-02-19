package vn.itz.jpastudying.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.Dto.StudentsDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.students.StudentsCreateForm;
import vn.itz.jpastudying.form.user.UserCreateForm;
import vn.itz.jpastudying.mapper.StudentsMapper;
import vn.itz.jpastudying.model.CustomUserDetails;
import vn.itz.jpastudying.model.Role;
import vn.itz.jpastudying.model.Students;
import vn.itz.jpastudying.projections.StudentInfoProjection;
import vn.itz.jpastudying.repository.RoleRepository;
import vn.itz.jpastudying.repository.StudentsRepository;
import vn.itz.jpastudying.repository.UserRepository;

@Service
public class AuthenticationService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private StudentsRepository studentsRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  private StudentsMapper studentsMapper;

  @Transactional
  public StudentsDto register(StudentsCreateForm request) {
    Role role = roleRepository.findRoleByKind(0).orElseThrow(()
    -> new ResourceNotFound("Vai tro nay khong ton tai", HttpStatus.NOT_FOUND));
    if (userRepository.existsByUsername(request.getUserNameValue()))
      throw new DuplicateEntityException("Username da ton tai");

    Students student = studentsMapper.convertToStudent(request);
    student.getUser().setAvatar("ImageEmpty");
    student.getUser().setPassword(passwordEncoder.encode(request.getPassWordValue()));
    student.getUser().setRole(role);
    return studentsMapper.convertToStudentResponse(studentsRepository.save(student));
  }

  public AuthenticationDto authenticate(UserCreateForm request){
    CustomUserDetails userDetails = customUserDetailService.validateUser(request.getUserNameValue(), request.getPassWordValue());

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUserNameValue(),
            request.getPassWordValue()
        )
    );

    String token = jwtService.generateToken(userDetails);

    return new AuthenticationDto(true, token);
  }

  public List<StudentInfoProjection> getAllStudentInfo(){
    return studentsRepository.findAllStudentInfo();
  }
}
