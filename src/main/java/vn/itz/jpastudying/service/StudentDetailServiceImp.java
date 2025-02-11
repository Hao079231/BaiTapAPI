package vn.itz.jpastudying.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.repository.StudentRepository;

@Service
public class StudentDetailServiceImp implements UserDetailsService {

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return studentRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFound("Sinh vien khong ton tai",
        HttpStatus.NOT_FOUND));
  }
}
