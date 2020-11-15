package com.prime.user.validation.password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    /**
     * Gets the default message.
     */
    String message() default "The fields must match";
    /**
     * Gets the default groups
     */
    Class<?>[] groups() default {};
    /**
     * Gets the default payload.
     */
    Class<? extends Payload>[] payload() default {};
    /**
     * Gets the first method.
     */
    String first();
    /**
     * Gets the secound method.
     */
    String second();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        /**
         * Gets the default mactches value.
         */
        PasswordMatches[] value();
    }

}
