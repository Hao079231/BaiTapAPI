package vn.itz.jpastudying.Dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SubjectCreateRequestDto {
  @NotNull(message = "Ten khoa hoc khong the null")
  @NotEmpty(message = "Ten khoa hoc khong the trong")
  String subjectName;

  @NotNull(message = "Ma khoa hoc khong the null")
  @NotEmpty(message = "Ma khoa hoc khong the trong")
  String subjectCode;
}
