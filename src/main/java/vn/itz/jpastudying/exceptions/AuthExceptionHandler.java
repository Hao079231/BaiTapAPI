package vn.itz.jpastudying.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.itz.jpastudying.Dto.ApiMessageDto;

@ControllerAdvice
// Xu ly loi dang nhap khong dung mat khau
public class AuthExceptionHandler {
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiMessageDto<String>> handleBadCredentialsException(
      BadCredentialsException ex) {
    ApiMessageDto<String> response = new ApiMessageDto<>(false, ex.getMessage(), null, ErrorCode.BAD_CREDENTIALS.getStatus());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  // Xu ly loi tai khoan khong co quyen thuc thi api yeu cau quyen
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiMessageDto<String>> handleAccessDeniedException(AccessDeniedException ex) {
    ApiMessageDto<String> response = new ApiMessageDto<>(
        false, ErrorCode.ACCESS_DENIED.getMessage(), null, ErrorCode.ACCESS_DENIED.getStatus());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }
}
