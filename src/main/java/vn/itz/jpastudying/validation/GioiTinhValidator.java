package vn.itz.jpastudying.validation;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GioiTinhValidator  implements ConstraintValidator<GioiTinh, String> {
  private static final List<String> VALID_GENDERS = Arrays.asList("1", "2", "3");
  @Override
  public void initialize(GioiTinh gioiTinh) {
    ConstraintValidator.super.initialize(gioiTinh);
  }

  @Override
  public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
    if (input == null || input.trim().isEmpty()) {
      return true;
    }
    return VALID_GENDERS.contains(input.trim());
  }
}
