package vn.itz.jpastudying.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = GioiTinhValidationImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GioiTinhValidation {
  String message() default "Gioi tinh khong hop le. Chi chap nhan gia tri: 1 (Nam), 2 (Nu), 3 (Khac).";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
