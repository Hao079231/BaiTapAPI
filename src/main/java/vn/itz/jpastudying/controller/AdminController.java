package vn.itz.jpastudying.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.form.admin.AdminCreateForm;
import vn.itz.jpastudying.service.AdminDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminDaoService adminDaoService;

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE_ADMIN')")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> createAdmin(@Valid @RequestBody AdminCreateForm request){
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results(
        "Dang ky thanh cong",
        adminDaoService.createAdmin(request)
    );
    return ResponseEntity.ok(response);
  }
}
