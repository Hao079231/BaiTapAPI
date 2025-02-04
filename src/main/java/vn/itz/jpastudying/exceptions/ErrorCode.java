package vn.itz.jpastudying.exceptions;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
  RESOURCE_NOT_FOUND("Khong tim thay tai nguyen", HttpStatus.NOT_FOUND),
  BAD_REQUEST("Du lieu yeu cau khong hop le", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("Loi may chu noi bo", HttpStatus.INTERNAL_SERVER_ERROR),
  DUPLICATE_RESOURCE("Tai nguyen da ton tai", HttpStatus.CONFLICT);

  private String message;
  private HttpStatus status;

  ErrorCode(String message, HttpStatus status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
