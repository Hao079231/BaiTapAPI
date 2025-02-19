package vn.itz.jpastudying.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
  private Long adminId;
  private String adminUserName;
  private String adminFullName;
  private String adminGender;
  private Integer adminLevel;
  private Boolean adminIsSuperAdmin;
}
