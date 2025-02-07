package vn.itz.jpastudying.Dto.request;

import lombok.Getter;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;

@Getter
public class SubjectRegistrationRequestDto {
  private Student student;
  private Subject subject;
}
