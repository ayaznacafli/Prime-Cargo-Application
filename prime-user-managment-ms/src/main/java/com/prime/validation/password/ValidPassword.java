package com.prime.validation.password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Target({
        ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Documented
public @interface ValidPassword {

    /**
     * Gets the default message.
     */
    String message() default "Invalid Password";
    /**
     * Gets the default groups
     */
    Class<?>[] groups() default {};
    /**
     * Gets the default payload.
     */
    Class<? extends Payload>[] payload() default {};
}
