package vn.itz.jpastudying.Dto.request;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Getter;
import vn.itz.jpastudying.validation.GioiTinhValidation;

@Getter
public class StudentUpdateRequestDto {
  @NotEmpty(message = "User khong the trong")
  private String userNameValue;

  @NotEmpty(message = "Fullname khong the trong")
  private String fullNameValue;

  @Past(message = "Ngay sinh phai la trong qua khu")
  private Date birthDateValue;

  @NotEmpty(message = "Password khong the trong")
  @Size(min = 5, message = "Password phai it nhat co 5 ky tu")
  private String passWordValue;

  @GioiTinhValidation
  private String genderValue;
}
