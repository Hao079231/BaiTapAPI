package vn.itz.jpastudying.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.itz.jpastudying.Dto.ApiMessageDto;

@ControllerAdvice
public class GlobalExceptionHandler {
  // Xu ly loi chung
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ApiMessageDto<String>> handleAllExceptions(Exception ex){
    ApiMessageDto<String> response =
        new ApiMessageDto<>(
            false,
            ErrorCode.INTERNAL_SERVER_ERROR.getMessage(),
            null,
            ErrorCode.INTERNAL_SERVER_ERROR.getStatus()
        );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  // Xu ly loi khong tim thay
  @ExceptionHandler(ResourceNotFound.class)
  public final ResponseEntity<ApiMessageDto<String>> handleUserNotFoundException(ResourceNotFound ex){
    ApiMessageDto<String> response =
        new ApiMessageDto<>(
            false,
            ex.getMessage(),
            null,
            ErrorCode.RESOURCE_NOT_FOUND.getStatus()
        );
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  // Xu ly loi Validate
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiMessageDto<List<HashMap<String, String>>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    List<HashMap<String, String>> results = ex
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(fieldError -> {
      HashMap<String, String> errors = new HashMap<>();
      errors.put("field", fieldError.getField());
      errors.put("message", fieldError.getDefaultMessage());
      return errors;
    }).collect(Collectors.toList());

    ApiMessageDto<List<HashMap<String, String>>> respone = new ApiMessageDto<>(
        false,
        ErrorCode.BAD_REQUEST.getMessage(),
        results,
        ErrorCode.BAD_REQUEST.getStatus()
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respone);
  }

  // Xu ly loi trung lap
  @ExceptionHandler(DuplicateEntityException.class)
  public ResponseEntity<ApiMessageDto<String>> hanldeDulicateEntityException(DuplicateEntityException ex){
    ApiMessageDto<String> respone = new ApiMessageDto<>(
        false,
        ex.getMessage(),
        null,
        ErrorCode.DUPLICATE_RESOURCE.getStatus()
    );
    return ResponseEntity.status(HttpStatus.CONFLICT).body(respone);
  }
}
