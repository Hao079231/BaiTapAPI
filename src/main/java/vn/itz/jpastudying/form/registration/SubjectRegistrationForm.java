package vn.itz.jpastudying.form.registration;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.itz.jpastudying.model.Student;
import vn.itz.jpastudying.model.Subject;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrationForm {
  private Student studentResponse;
  private Subject subjectResponse;
  private String statusResponse;
  private Date dateRegisterResponse;
  private float studyResultResponse;}
