package vn.itz.jpastudying.Dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubjectUpdateRequestDto {
  @NotNull(message = "Ten khoa hoc khong the null")
  @NotEmpty(message = "Ten khoa hoc khong the trong")
  private String subjectName;

  @NotNull(message = "Ma khoa hoc khong the null")
  @NotEmpty(message = "Ma khoa hoc khong the trong")
  private String subjectCode;

  public String getSubjectName() {
    return subjectName;
  }

  public String getSubjectCode() {
    return subjectCode;
  }
}
