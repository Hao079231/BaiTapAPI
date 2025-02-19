package vn.itz.jpastudying.form.lecturer;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.itz.jpastudying.validation.GioiTinhValidation;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerCreateForm {
  @ApiModelProperty(value = "Ten user", example = "vana", required = true)
  @NotEmpty(message = "User khong the trong")
  private String userNameValue;

  @ApiModelProperty(value = "Ten day du", example = "Nguyen Van A", required = true)
  @NotEmpty(message = "Fullname khong the trong")
  private String fullNameValue;

  @ApiModelProperty(value = "Mat khau", example = "Vana@03190", required = true)
  @NotEmpty(message = "Mat khau khong the trong")
  @Size(min = 6, message = "Mat khau phai it nhat co 6 ki tu")
  @Pattern(
      regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{6,}$",
      message = "Mat khau phai co it nhat 1 ki tu viet hoa, 1 ki tu dac biet va toi thieu phai co 6 ki tu"
  )
  private String passWordValue;

  @ApiModelProperty(value = "Gioi tinh: 1 - nam, 2 - nu, 3 - khac", example = "1", required = false)
  @GioiTinhValidation
  private String genderValue;

  @ApiModelProperty(value = "Chuyen nganh cua giao vien", example = "Cong nghe phan mem", required = true)
  @NotEmpty(message = "Chuyen nganh khong the trong")
  private String careerValue;
}
