package vn.itz.jpastudying.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.admin.AdminCreateForm;
import vn.itz.jpastudying.model.Admin;
import vn.itz.jpastudying.model.Role;
import vn.itz.jpastudying.model.User;
import vn.itz.jpastudying.projections.AdminInfoProjection;
import vn.itz.jpastudying.repository.AdminRepository;
import vn.itz.jpastudying.repository.RoleRepository;
import vn.itz.jpastudying.repository.UserRepository;

@Service
public class AdminDaoService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional
  public AuthenticationDto createAdmin(AdminCreateForm request){
    Role role = roleRepository.findRoleByKind(request.getRoleValue()).orElseThrow(
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

    Admin admin = new Admin();
    admin.setId(user.getId());
    admin.setUser(user);
    admin.setLevel(request.getLevelValue());
    admin.setSuperAdmin(request.getIsSuperAdminValue());

    user.setAdmin(admin);

    userRepository.save(user);
    adminRepository.save(admin);

    return new AuthenticationDto(true, "Dang ky admin thanh cong, thong tin da duoc luu vao he thong");
  }

  public List<AdminInfoProjection> getAllAdminInfo(){
    return adminRepository.findAllAdminInfo();
  }
}
