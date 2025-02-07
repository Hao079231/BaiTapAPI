package vn.itz.jpastudying.Dto.response;

import java.util.Date;
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
public class StudentResponseDto {
  private int studentId;
  private String userName;
  private String fullName;
  private Date birthDate;
  private String passWord;
  private String genDer;
}
