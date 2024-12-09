package com.ra.projectmd3test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserNameConstraint.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameUnique {
    String message() default "User name has been exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
