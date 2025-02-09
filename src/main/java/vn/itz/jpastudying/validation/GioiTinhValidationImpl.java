package vn.itz.jpastudying.validation;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GioiTinhValidationImpl implements ConstraintValidator<GioiTinhValidation, String> {
  private static final List<String> VALID_GENDERS = List.of("1", "2", "3");
  @Override
  public void initialize(GioiTinhValidation gioiTinhValidation) {
    ConstraintValidator.super.initialize(gioiTinhValidation);
  }

  @Override
  public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
    if (input == null || input.trim().isEmpty()) {
      return true;
    }
    return VALID_GENDERS.contains(input.trim());
  }
}
