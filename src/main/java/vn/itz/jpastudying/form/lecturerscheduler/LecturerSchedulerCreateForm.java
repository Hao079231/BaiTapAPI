package vn.itz.jpastudying.form.lecturerscheduler;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerCreateForm {

  @ApiModelProperty(value = "Id cua giang vien", example = "1232", required = true)
  @NotNull(message = "Id cua giang vien khong the trong")
  private Long lecturerIdValue;

  @ApiModelProperty(value = "Id cua khoa hoc", example = "1", required = true)
  @NotNull(message = "Id cua khoa hoc khong the trong")
  private int courseIdValue;

  @ApiModelProperty(value = "Id cua hoc ky", example = "2", required = true)
  @NotNull(message = "Id cua hoc ky khong the trong")
  private Long periodId;
}
