package vn.itz.jpastudying.Dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SubjectResponseDto {
  private int subjectId;
  private String subjectName;
  private String subjectCode;

  public SubjectResponseDto(int subjectId, String subjectName, String subjectCode) {
    this.subjectId = subjectId;
    this.subjectName = subjectName;
    this.subjectCode = subjectCode;
  }
}
