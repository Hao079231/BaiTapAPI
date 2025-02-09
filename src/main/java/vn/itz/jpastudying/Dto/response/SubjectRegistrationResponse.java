package vn.itz.jpastudying.Dto.response;

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
public class SubjectRegistrationResponse {
  private Long idResponse;
  private StudentResponseDto studentResponse;
  private SubjectResponseDto subjectResponse;
  private String dateRegisterResponse;
  private String statusResponse;
}
