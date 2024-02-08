package monday.budget_manager.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import monday.budget_manager.validation.FieldMatchValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMatch {
  String message() default "Pola muszą być takie same.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String first();

  String second();

  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {
    FieldMatch[] value();
  }
}
