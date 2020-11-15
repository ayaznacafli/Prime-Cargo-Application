package com.prime.order.exception;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.prime.order.dto.ErrorDto;
import java.io.IOException;
import java.util.Calendar;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ObjectError error = ex.getBindingResult().getAllErrors().stream().findAny().orElse(null);
        FieldError fieldError = (FieldError) error;
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                fieldError.getDefaultMessage(),
                fieldError.getDefaultMessage(),
                Calendar.getInstance());
        log.warn(fieldError.getDefaultMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity handleOrderNotFoundException(OrderNotFoundException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotUpdateException.class)
    public ResponseEntity handleOrderNotUpdateException(OrderNotUpdateException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity handleInvalidDefinitionException(InvalidDefinitionException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotDeleteException.class)
    public ResponseEntity handleOrderNotDeleteException(OrderNotDeleteException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderDateInfoNotFoundException.class)
    public ResponseEntity handleOrderDateInfoNotFoundException(OrderDateInfoNotFoundException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(OrderQuantityNotFoundException.class)
    public ResponseEntity handleOrderQuantityNotFoundException(OrderQuantityNotFoundException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                ex.getMessage(),
                Calendar.getInstance());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex)
            throws IOException {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(),
                getConstraintViolationExceptionMessage(ex),
                getConstraintViolationExceptionMessage(ex),
                Calendar.getInstance());
        log.warn(getConstraintViolationExceptionMessage(ex));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    private String getConstraintViolationExceptionMessage(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()).get(0);
    }


}
