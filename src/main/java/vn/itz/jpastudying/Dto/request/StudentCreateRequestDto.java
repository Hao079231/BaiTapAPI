package vn.itz.jpastudying.Dto.request;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.Getter;
import vn.itz.jpastudying.validation.GioiTinh;

@Getter
public class StudentCreateRequestDto {
  @NotEmpty(message = "User khong the trong")
  private String userName;

  @NotEmpty(message = "Fullname khong the trong")
  private String fullName;

  @NotEmpty(message = "Birthday khong the trong")
  @Past(message = "Ngay sinh phai la trong qua khu")
  private Date birthDate;

  @NotEmpty(message = "Password khong the trong")
  @Size(min = 5, message = "Password phai it nhat co 5 ky tu")
  private String passWord;

  @GioiTinh
  private String genDer;
}
