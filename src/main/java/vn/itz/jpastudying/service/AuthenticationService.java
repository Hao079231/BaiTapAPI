package vn.itz.jpastudying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.enums.Role;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.student.StudentCreateForm;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.repository.StudentRepository;

@Service
public class AuthenticationService {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  public AuthenticationDto register(StudentCreateForm request){
    Student student = new Student();
    student.setUsername(request.getUserNameValue());
    student.setFullname(request.getFullNameValue());
    student.setBirthday(request.getBirthDateValue());
    student.setPassword(passwordEncoder.encode(request.getPassWordValue()));
    student.setGender(request.getGenderValue());
    student.setRole(Role.valueOf(request.getRoleValue()));
    student.setAuthorities(request.getAuthoritiesValue());

    student = studentRepository.save(student);
    String token = jwtService.generateToken(student);
    return new AuthenticationDto(true, token);
  }

  public AuthenticationDto authenticate(StudentCreateForm request){
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUserNameValue(),
            request.getPassWordValue()
        )
    );
    Student student = studentRepository.findByUsername(request.getUserNameValue()).orElseThrow(
        () -> new ResourceNotFound("Sinh vien khong ton tai", HttpStatus.NOT_FOUND));
    String token = jwtService.generateToken(student);

    return new AuthenticationDto(true, token);
  }
}
