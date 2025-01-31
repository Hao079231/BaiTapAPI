package vn.itz.jpastudying.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException<T> {
  private final boolean result;
  private final String message;
  private final T data;
  private final HttpStatus httpStatus;

  public ApiException(Boolean result, String message, T data, HttpStatus httpStatus) {
    this.result = result;
    this.message = message;
    this.data = data;
    this.httpStatus = httpStatus;
  }

  public boolean isResult() {
    return result;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
