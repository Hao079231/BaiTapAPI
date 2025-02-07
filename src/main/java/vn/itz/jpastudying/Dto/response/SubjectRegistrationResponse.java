package vn.itz.jpastudying.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrationResponse {
  private Student studentResponse;
  private Subject subjectResponse;
}
