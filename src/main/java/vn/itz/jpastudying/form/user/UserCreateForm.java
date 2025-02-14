package vn.itz.jpastudying.form.user;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.itz.jpastudying.validation.GioiTinhValidation;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateForm {

  @ApiModelProperty(value = "Ten dang nhap", example = "vana", required = true)
  @NotEmpty(message = "Username khong the trong")
  private String userNameValue;

  @ApiModelProperty(value = "Ten day du", example = "Nguyen Van A", required = true)
  @NotEmpty(message = "Fullname khong the trong")
  private String fullNameValue;


  @ApiModelProperty(value = "Mat khau", example = "Vana@03190", required = true)
  @NotEmpty(message = "Mat khau khong the trong")
  @Pattern(
      regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{6,}$",
      message = "Mat khau phải co 1 ky tu viet hoa, 1 ky tu dac biet va toi thieu co 6 ky tu"
  )
  private String passWordValue;

  @ApiModelProperty(value = "Gioi tinh: 1 - Nam, 2 - Nu, 3 - Khac", example = "1", required = false)
  @GioiTinhValidation
  private String genderValue;

  @ApiModelProperty(value = "Quyen của user: 1 - Admin, 2 - Student", example = "1", required = true)
  private Long roleValue;

  private String avatarValue;
}
