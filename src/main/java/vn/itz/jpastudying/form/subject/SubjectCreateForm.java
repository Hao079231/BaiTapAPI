package vn.itz.jpastudying.form.subject;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCreateForm {
  @ApiModelProperty(value = "Ten khoa hoc", example = "Lap trinh web", required = true)
  @NotEmpty(message = "Ten khoa hoc khong the trong")
  private String subjectNameValue;

  @ApiModelProperty(value = "Ma khoa hoc", example = "ltw", required = true)
  @NotEmpty(message = "Ma khoa hoc khong the trong")
  private String subjectCodeValue;

  @ApiModelProperty(value = "Trang thai khoa hoc (1: Hoan thanh, 0: Chua hoan thanh)", example = "1", required = true)
  private boolean subjectStatusValue;
}
