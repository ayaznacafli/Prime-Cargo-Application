package com.prime.user.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);

            if (!valid) {
                context.buildConstraintViolationWithTemplate(message)
                        .addPropertyNode(firstFieldName)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Passwor matches validator error {}",e);
        }
        return valid;
    }
}
