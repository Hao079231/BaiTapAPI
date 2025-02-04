package vn.itz.jpastudying.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(Include.NON_NULL)
public class ApiMessageDto<T> {
  private boolean result;
  private String message;
  private T data;
  private HttpStatus status;

  public ApiMessageDto() {
  }

  public ApiMessageDto(boolean result, String message, T data, HttpStatus status) {
    this.result = result;
    this.message = message;
    this.data = data;
    this.status = status;
  }

  public boolean isResult() {
    return result;
  }

  public void setResult(boolean result) {
    this.result = result;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
