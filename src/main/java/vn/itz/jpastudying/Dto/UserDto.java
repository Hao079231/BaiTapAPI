package vn.itz.jpastudying.Dto;

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
public class UserDto {
  private Long studentIdValue;
  private String mssvValue;
  private String userNameValue;
  private String fullNameValue;
  private Date birthDateValue;
  private String genderValue;
  private String roleValue;
}
