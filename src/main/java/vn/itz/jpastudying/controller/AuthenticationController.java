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
import vn.itz.jpastudying.form.student.StudentCreateForm;
import vn.itz.jpastudying.service.AuthenticationService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @PostMapping("/token/register")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> register(@Valid @RequestBody StudentCreateForm request) {
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results(
        "Dang ky thanh cong",
        authService.register(request)
    );
    return ResponseEntity.ok(response);
  }

  @PostMapping("/token/login")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> login(@RequestBody StudentCreateForm request) {
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results(
        "Dang nhap thanh cong",
        authService.authenticate(request)
    );
    return ResponseEntity.ok(response);
  }
}
