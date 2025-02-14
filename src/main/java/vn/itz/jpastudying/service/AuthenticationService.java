package vn.itz.jpastudying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.student.StudentActivateForm;
import vn.itz.jpastudying.form.user.UserCreateForm;
import vn.itz.jpastudying.model.CustomUserDetails;
import vn.itz.jpastudying.model.Role;
import vn.itz.jpastudying.model.Students;
import vn.itz.jpastudying.model.User;
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

  public AuthenticationDto register(UserCreateForm request) {
    Role role = roleRepository.findById(request.getRoleValue()).orElseThrow(
        () -> new ResourceNotFound("Vai tro cua user khong ton tai", HttpStatus.NOT_FOUND));
    if (userRepository.existsByUsername(request.getUserNameValue()))
      throw new DuplicateEntityException("Username da ton tai");

    User user = new User();
    user.setUsername(request.getUserNameValue());
    user.setFullname(request.getFullNameValue());
    user.setPassword(passwordEncoder.encode(request.getPassWordValue()));
    user.setGender(request.getGenderValue());
    user.setAvatar(request.getAvatarValue() != null ? request.getAvatarValue() : "");
    user.setRole(role);
    user = userRepository.save(user);

    return new AuthenticationDto(true, "Dang ky thanh cong, vui long kich hoat tai khoan sinh vien");
  }

  // Kich hoat user thanh student
  public AuthenticationDto activateStudent(StudentActivateForm request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ResourceNotFound("User khong ton tai", HttpStatus.NOT_FOUND));

    if (studentsRepository.findByUser(user).isPresent()) {
      throw new DuplicateEntityException("User nay da la sinh vien");
    }

    Students student = new Students();
    student.setUser(user);
    student.setMssv(request.getMssvValue());
    student.setBirthday(request.getBirthDateValue());
    studentsRepository.save(student);

    return new AuthenticationDto(true, "Kich hoat tai khoan sinh vien thanh cong");
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
}
