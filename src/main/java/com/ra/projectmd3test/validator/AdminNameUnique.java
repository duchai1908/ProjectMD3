package com.ra.projectmd3test.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AdminNameConstraint.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminNameUnique {
    String message() default "Admin name has been exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
