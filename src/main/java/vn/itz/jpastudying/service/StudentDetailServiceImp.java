package vn.itz.jpastudying.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.repository.StudentRepository;

@Service
public class StudentDetailServiceImp implements UserDetailsService {

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return studentRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFound("Sinh vien khong ton tai",
        HttpStatus.NOT_FOUND));
  }

  public Student validateUser(String username, String rawPassword) {
    Student student = (Student) loadUserByUsername(username);

    if (!passwordEncoder.matches(rawPassword, student.getPassword())) {
      throw new BadCredentialsException("Mat khau khong dung");
    }
    return student;
  }

  // Ham lay danh sach cac quyen tu UserDetails
  public List<String> getUserAuthorities(UserDetails userDetails) {
    return userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());
  }
}
