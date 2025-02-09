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
public class SubjectResponseDto {
  private int subjectIdValue;
  private String subjectNameValue;
  private String subjectCodeValue;
  private boolean subjectStatusValue;
}
