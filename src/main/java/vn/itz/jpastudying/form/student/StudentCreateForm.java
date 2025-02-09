package vn.itz.jpastudying.form.student;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.itz.jpastudying.validation.GioiTinhValidation;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreateForm {
  @ApiModelProperty(value = "Ten user", example = "vana", required = true)
  @NotEmpty(message = "User khong the trong")
  private String userNameValue;

  @ApiModelProperty(value = "Ten day du", example = "Nguyen Van A", required = true)
  @NotEmpty(message = "Fullname khong the trong")
  private String fullNameValue;

  @ApiModelProperty(value = "Ngay sinh sinh vien (phai o trong qua khu)", example = "2001-02-20", required = true)
  @Past(message = "Ngay sinh phai la trong qua khu")
  private Date birthDateValue;


  @ApiModelProperty(value = "Mat khau", example = "vana03190", required = true)
  @NotEmpty(message = "Password khong the trong")
  @Size(min = 5, message = "Password phai it nhat co 5 ky tu")
  private String passWordValue;

  @ApiModelProperty(value = "Gioi tinh: 1 - nam, 2 - nu, 3 - khac", example = "1", required = false)
  @GioiTinhValidation
  private String genderValue;
}
