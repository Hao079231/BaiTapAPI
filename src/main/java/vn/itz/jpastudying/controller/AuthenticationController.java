package vn.itz.jpastudying.controller;

import com.nimbusds.jose.JOSEException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.Dto.StudentDto;
import vn.itz.jpastudying.form.authentication.AuthenticationForm;
import vn.itz.jpastudying.form.authentication.IntrospectForm;
import vn.itz.jpastudying.service.AuthenticationService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  // Lay token tu viec nhap username, password
  @PostMapping("/token")
  public ResponseEntity<ApiMessageDto<AuthenticationDto>> authenticate(@RequestBody
      AuthenticationForm request){
    AuthenticationDto result = authenticationService.authenticate(request);
    ApiMessageDto<AuthenticationDto> response = ApiResponeUtils.results("Ket qua", result);
    return ResponseEntity.ok(response);
  }

  // Tra ve thong tin chi tiet cua mot sinh vien khi nhap token
  @PostMapping("/introspect")
  public ResponseEntity<ApiMessageDto<StudentDto>> authenticate(@RequestBody
  IntrospectForm request) throws ParseException, JOSEException {
    StudentDto result = authenticationService.introspect(request);
    ApiMessageDto<StudentDto> response = ApiResponeUtils.results("Ket qua", result);
    return ResponseEntity.ok(response);
  }
}
