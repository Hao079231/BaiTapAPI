package vn.itz.jpastudying.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(value = {ApiRequestException.class})
  public ResponseEntity<ApiException<String>> handleApiRequestException(ApiRequestException ex) {
    HttpStatus status = ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.BAD_REQUEST;

    ApiException<String> response = new ApiException<>(
        false,
        ex.getMessage(),
        null,
        status
    );
    return ResponseEntity.status(status).body(response);
  }
}
