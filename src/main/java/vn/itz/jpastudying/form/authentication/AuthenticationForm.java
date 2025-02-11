package vn.itz.jpastudying.form.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationForm {
  private String usernameValue;
  private String passwordValue;
}
