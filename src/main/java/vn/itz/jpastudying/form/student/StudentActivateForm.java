package vn.itz.jpastudying.form.student;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentActivateForm {
  @ApiModelProperty(value = "ID cua user can kich hoat", example = "1", required = true)
  @NotNull(message = "UserId khong the trong")
  private Long userId;

  @ApiModelProperty(value = "MSSV", example = "20230001", required = true)
  @NotEmpty(message = "MSSV khong the trong")
  private String mssvValue;


  @ApiModelProperty(value = "Ngay sinh (phai o trong qua khu)", example = "2001-02-20", required = true)
  @Past(message = "Ngay sinh phai la o qua khu")
  private Date birthDateValue;
}
