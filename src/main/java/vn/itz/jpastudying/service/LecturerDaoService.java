package vn.itz.jpastudying.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.jpastudying.Dto.LecturerDto;
import vn.itz.jpastudying.exceptions.DuplicateEntityException;
import vn.itz.jpastudying.exceptions.ResourceNotFound;
import vn.itz.jpastudying.form.lecturer.LecturerCreateForm;
import vn.itz.jpastudying.mapper.LecturerMapper;
import vn.itz.jpastudying.model.Lecturer;
import vn.itz.jpastudying.model.Role;
import vn.itz.jpastudying.repository.LecturerRepository;
import vn.itz.jpastudying.repository.RoleRepository;
import vn.itz.jpastudying.repository.UserRepository;

@Service
public class LecturerDaoService {
  @Autowired
  private LecturerRepository lecturerRepository;

  @Autowired
  private LecturerMapper lecturerMapper;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Transactional
  public LecturerDto registerLecturer(LecturerCreateForm request){
    Role role = roleRepository.findRoleByKind(2).orElseThrow(
        () -> new ResourceNotFound("Vai tro lecturer cua user khong ton tai", HttpStatus.NOT_FOUND));
    if (userRepository.existsByUsername(request.getUserNameValue()))
      throw new DuplicateEntityException("Username da ton tai");

    Lecturer lecturer = lecturerMapper.convertToLecturer(request);
    lecturer.getUser().setAvatar("ImageEmpty");
    lecturer.getUser().setPassword(passwordEncoder.encode(request.getPassWordValue()));
    lecturer.getUser().setRole(role);
    return lecturerMapper.convertToLecturerResponse(lecturerRepository.save(lecturer));
  }
}
