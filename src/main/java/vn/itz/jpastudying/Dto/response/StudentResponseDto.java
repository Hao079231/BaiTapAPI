package vn.itz.jpastudying.Dto.response;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
public class StudentResponseDto {
  private int studentId;
  private String userName;
  private String fullName;
  private Date birthDate;
  private String passWord;

  public StudentResponseDto(int studentId, String userName, String fullName, Date birthDate,
      String passWord) {
    this.studentId = studentId;
    this.userName = userName;
    this.fullName = fullName;
    this.birthDate = birthDate;
    this.passWord = passWord;
  }
}
