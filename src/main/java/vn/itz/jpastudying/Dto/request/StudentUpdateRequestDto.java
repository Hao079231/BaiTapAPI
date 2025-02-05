package vn.itz.jpastudying.Dto.request;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.Getter;

@Getter
public class StudentUpdateRequestDto {
  @NotNull(message = "Username khong the null")
  @NotEmpty(message = "User khong the trong")
  private String userName;

  @NotNull(message = "Fullname khong the null")
  @NotEmpty(message = "Fullname khong the trong")
  private String fullName;

  @NotNull(message = "Birthday khong the null")
  @Past(message = "Ngay sinh phai la trong qua khu")
  private Date birthDate;

//  public String getUserName() {
//    return userName;
//  }
//
//  public String getFullName() {
//    return fullName;
//  }
//
//  public Date getBirthDate() {
//    return birthDate;
//  }
}
