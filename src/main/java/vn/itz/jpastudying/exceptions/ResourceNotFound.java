package vn.itz.jpastudying.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends RuntimeException{
  private HttpStatus httpStatus;

  public ResourceNotFound(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
