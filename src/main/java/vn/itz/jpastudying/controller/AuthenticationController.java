package vn.itz.jpastudying.controller;

import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.jpastudying.Dto.ApiMessageDto;
import vn.itz.jpastudying.Dto.AuthenticationDto;
import vn.itz.jpastudying.Dto.StudentsDto;
import vn.itz.jpastudying.form.students.StudentsCreateForm;
import vn.itz.jpastudying.form.user.UserCreateForm;
import vn.itz.jpastudying.projections.StudentInfoProjection;
import vn.itz.jpastudying.service.AuthenticationService;
import vn.itz.jpastudying.utils.ApiResponeUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationService authService;

  @PostMapping("/register")
  public ResponseEntity<ApiMessageDto<StudentsDto>> register(@Valid @RequestBody StudentsCreateForm request) {
    ApiMessageDto<StudentsDto> response = ApiResponeUtils.results(
        "Dang ky thanh cong",
        authService.register(request)
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

  @GetMapping("/student/list")
  public ResponseEntity<ApiMessageDto<List<StudentInfoProjection>>> getAllStudentInfo(){
    ApiMessageDto<List<StudentInfoProjection>> response = ApiResponeUtils.results("Danh sach thong tin sinh vien",
        authService.getAllStudentInfo());
    return ResponseEntity.ok(response);
  }
}
