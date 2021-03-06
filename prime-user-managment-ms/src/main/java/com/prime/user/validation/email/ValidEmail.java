package com.prime.user.validation.email;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    /**
     * Gets the default message.
     */
    String message() default "Invalid Email";
    /**
     * Gets the default groups
     */
    Class<?>[] groups() default {};
    /**
     * Gets the default payload.
     */
    Class<? extends Payload>[] payload() default {};
}
