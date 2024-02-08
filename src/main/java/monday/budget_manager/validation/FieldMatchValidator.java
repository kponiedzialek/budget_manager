package monday.budget_manager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import monday.budget_manager.validation.annotation.FieldMatch;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
  private String firstFieldName;
  private String secondFieldName;

  @Override
  public void initialize(final FieldMatch constraintAnnotation) {
    firstFieldName = constraintAnnotation.first();
    secondFieldName = constraintAnnotation.second();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    try {
      String firstValue = (String) new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
      String secondValue = (String) new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

      return firstValue == null && secondValue == null
          || firstValue != null && firstValue.equals(secondValue);
    } catch (Exception ignored) {

    }
    return true;
  }
}
