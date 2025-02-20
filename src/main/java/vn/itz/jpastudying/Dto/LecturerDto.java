package vn.itz.jpastudying.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDto {
  private Long lecturerId;
  private String lecturerUserName;
  private String lecturerFullName;
  private String lecturerGender;
  private String lecturerCareer;
}
