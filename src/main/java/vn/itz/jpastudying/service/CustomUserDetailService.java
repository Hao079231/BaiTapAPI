package vn.itz.jpastudying.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.model.Admin;
import vn.itz.jpastudying.model.CustomUserDetails;
import vn.itz.jpastudying.model.Students;
import vn.itz.jpastudying.model.User;
import vn.itz.jpastudying.repository.AdminRepository;
import vn.itz.jpastudying.repository.RoleRepository;
import vn.itz.jpastudying.repository.StudentsRepository;
import vn.itz.jpastudying.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private StudentsRepository studentsRepository;

  @Autowired
  private AdminRepository adminRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User khong ton tai"));

    Optional<Students> studentOpt = studentsRepository.findByUser(user);
    Students student = studentOpt.orElse(null);

    Optional<Admin> adminOpt = adminRepository.findByUser(user);
    Admin admin = adminOpt.orElse(null);

    return new CustomUserDetails(user, student, admin);
  }

  public CustomUserDetails validateUser(String username, String rawPassword) {
    CustomUserDetails userDetails = (CustomUserDetails) loadUserByUsername(username);

    if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
      throw new BadCredentialsException("Mat khau khong dung");
    }

    return userDetails;
  }
}
