package com.prime.common.exception;

import com.prime.common.HttpResponseConstants;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends DefaultErrorAttributes {

    private static final String ARGUMENT_VALIDATION_FAILED = "Argument validation failed";

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handle(AuthenticationException ex,
                                                      WebRequest request) {
        log.trace("Authentication failed {}", ex.getMessage());
        return ofType(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Map<String, Object>> handle(NotFoundException ex,
                                                            WebRequest request) {
        log.trace("Resource not found {}", ex.getMessage());
        return ofType(request, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidStateException.class)
    public final ResponseEntity<Map<String, Object>> handle(InvalidStateException ex,
                                                            WebRequest request) {
        log.trace("Request is invalid state {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Map<String, Object>> handle(ConstraintViolationException ex,
                                                            WebRequest request) {
        log.trace("Constraints violated {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, getConstraintViolationExceptionMessage(ex));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Map<String, Object>> handle(MethodArgumentTypeMismatchException ex,
                                                            WebRequest request) {
        log.trace("Method arguments are not valid {}", ex.getMessage());
        return ofType(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, Object>> handle(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        List<ConstraintsViolationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ConstraintsViolationError(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ofType(request, HttpStatus.BAD_REQUEST, ARGUMENT_VALIDATION_FAILED, validationErrors);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message) {
        return ofType(request, status, message, Collections.EMPTY_LIST);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message,
                                                       List validationErrors) {
        Map<String, Object> attributes = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        attributes.put(HttpResponseConstants.STATUS, status.value());
        attributes.put(HttpResponseConstants.ERROR, status.getReasonPhrase());
        attributes.put(HttpResponseConstants.MESSAGE, message);
        attributes.put(HttpResponseConstants.ERRORS, validationErrors);
        attributes.put(HttpResponseConstants.PATH, ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, status);
    }

    private String getConstraintViolationExceptionMessage(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0);
    }
}

