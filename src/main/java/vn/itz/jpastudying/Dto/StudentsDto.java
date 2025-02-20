package vn.itz.jpastudying.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentsDto {
  private Long studentIdValue;
  private String userNameValue;
  private String fullNameValue;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date birthDateValue;
  private String genderValue;
  private String mssvValue;
}
