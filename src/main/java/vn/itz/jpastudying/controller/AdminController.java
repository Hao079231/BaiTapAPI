package vn.itz.jpastudying.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.AdminDto;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.form.admin.AdminCreateForm;
import vn.itz.jpastudying.projections.AdminInfoProjection;
import vn.itz.jpastudying.service.AdminDaoService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private AdminDaoService adminDaoService;

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE_ADMIN')")
  public ResponseEntity<ApiMessageDto<AdminDto>> createAdmin(@Valid @RequestBody AdminCreateForm request){
    ApiMessageDto<AdminDto> response = ApiResponeUtils.results(
        "Dang ky thanh cong",
        adminDaoService.createAdmin(request)
    );
    return ResponseEntity.ok(response);
  }

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<List<AdminInfoProjection>>> getAllAdminInfo(){
    ApiMessageDto<List<AdminInfoProjection>> response = ApiResponeUtils.results(
        "Danh sach thong tin sinh vien",
        adminDaoService.getAllAdminInfo()
    );
    return ResponseEntity.ok(response);
  }
}
