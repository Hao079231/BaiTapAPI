package vn.itz.jpastudying.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.AdminDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.admin.AdminCreateForm;
import vn.itz.jpastudying.mapper.AdminMapper;
import vn.itz.jpastudying.model.Admin;
import vn.itz.jpastudying.model.Role;
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

  @Autowired
  private AdminMapper adminMapper;

  @Transactional
  public AdminDto createAdmin(AdminCreateForm request){
    Role role = roleRepository.findRoleByKind(1).orElseThrow(
        () -> new ResourceNotFound("Vai tro admin cua user khong ton tai", HttpStatus.NOT_FOUND));
    if (userRepository.existsByUsername(request.getUserNameValue()))
      throw new DuplicateEntityException("Username da ton tai");

    Admin admin = adminMapper.convertToAdmin(request);
    admin.getUser().setAvatar("ImageEmpty");
    admin.getUser().setPassword(passwordEncoder.encode(request.getPassWordValue()));
    admin.getUser().setRole(role);
    admin.setSuperAdmin(false);

    return adminMapper.convertToAdminResponse(adminRepository.save(admin));
  }

  public List<AdminInfoProjection> getAllAdminInfo(){
    return adminRepository.findAllAdminInfo();
  }
}
