package vn.itz.jpastudying.Dto;

import java.util.Date;
import java.util.Set;
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
public class StudentDto {
  private int studentIdValue;
  private String userNameValue;
  private String fullNameValue;
  private Date birthDateValue;
  private String passWordValue;
  private String genderValue;
  private String roleValue;
}
