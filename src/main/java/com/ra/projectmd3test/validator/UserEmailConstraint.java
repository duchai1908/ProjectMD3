package com.ra.projectmd3test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserEmailConstraint implements ConstraintValidator<UserEmailValid,String> {
    private static final String EMAIL_PATTERN = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public void initialize(UserEmailValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && pattern.matcher(email).matches();
    }
}
