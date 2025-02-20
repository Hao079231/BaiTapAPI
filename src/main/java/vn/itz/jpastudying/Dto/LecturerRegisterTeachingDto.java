package vn.itz.jpastudying.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerRegisterTeachingDto {
  private Long lecturerIdValue;
  private int courseIdValue;
  private Long periodIdValue;
}
