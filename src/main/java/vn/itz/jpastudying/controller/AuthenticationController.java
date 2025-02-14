package vn.itz.jpastudying.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.form.student.StudentActivateForm;
import vn.itz.jpastudying.form.user.UserCreateForm;
import vn.itz.jpastudying.service.AuthenticationService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @PostMapping("/register")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> register(@Valid @RequestBody UserCreateForm request) {
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results(
        "Dang ky thanh cong",
        authService.register(request)
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping("/activate-student")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> activateStudent(@Valid @RequestBody StudentActivateForm request) {
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results(
        "Kich hoat thanh cong",
        authService.activateStudent(request)
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping("/token/login")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> login(@RequestBody UserCreateForm request) {
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results(
        "Dang nhap thanh cong",
        authService.authenticate(request)
    );
    return ResponseEntity.ok(response);
  }
}
