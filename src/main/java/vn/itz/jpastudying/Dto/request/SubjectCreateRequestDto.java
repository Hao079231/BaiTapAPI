package vn.itz.jpastudying.Dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SubjectCreateRequestDto {
  @NotEmpty(message = "Ten khoa hoc khong the trong")
  String subjectNameValue;

  @NotEmpty(message = "Ma khoa hoc khong the trong")
  String subjectCodeValue;
}
