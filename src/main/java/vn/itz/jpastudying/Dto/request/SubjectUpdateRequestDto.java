package vn.itz.jpastudying.Dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubjectUpdateRequestDto {
  @NotNull(message = "Ten khoa hoc khong the null")
  @NotEmpty(message = "Ten khoa hoc khong the trong")
  String subjectName;

  public String getSubjectName() {
    return subjectName;
  }
}
