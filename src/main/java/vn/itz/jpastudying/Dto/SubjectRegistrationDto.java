package vn.itz.jpastudying.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRegistrationDto {
  private Long idResponse;
  private StudentDto studentResponse;
  private SubjectDto subjectResponse;
  private String dateRegisterResponse;
  private String statusResponse;
}
