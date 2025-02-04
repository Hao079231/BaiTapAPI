package vn.itz.jpastudying.utils;

import org.springframework.http.HttpStatus;
import vn.itz.jpastudying.Dto.ApiMessageDto;

public class ApiResponeUtils {
  public static <T> ApiMessageDto<T> results(String message, T data){
    ApiMessageDto<T> respone = new ApiMessageDto<>();
      respone.setResult(true);
      respone.setMessage(message);
      respone.setData(data);
      respone.setStatus(HttpStatus.OK);
      return respone;
  }
}
