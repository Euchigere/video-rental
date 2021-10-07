package com.emmanuelc.videorental.exceptions;

import com.emmanuelc.videorental.domain.dto.ApiResponse;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException (ResourceNotFoundException exe) {
        return buildResponseEntity(exe.getMessage(), exe.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException cve) {
        return buildResponseEntity(
                getValidationErrors(cve.getConstraintViolations()),
                "constraint violations",
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(
                getValidationErrors(ex.getBindingResult().getFieldErrors()),
                "please fill in all required fields",
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL());
        return buildResponseEntity(message, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity("Malformed JSON request", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity("Error writing JSON output", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildResponseEntity(String message, HttpStatus status) {
        final ApiResponse<?> response = new ApiResponse<>();
        response.setMessage(message);
        response.setData(null);
        response.setStatus(false);
        response.setTimestamp(ZonedDateTime.now());
        return buildResponseEntity(response, status);
    }

    private ResponseEntity<Object> buildResponseEntity(Map<String, String> errors, String message, HttpStatus status) {
        final ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setMessage(message);
        response.setData(errors);
        response.setStatus(false);
        response.setTimestamp(ZonedDateTime.now());
        return buildResponseEntity(response, status);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiResponse<?> apiResponse, HttpStatus status) {
        return new ResponseEntity<>(apiResponse, status);
    }

    private Map<String, String> getValidationErrors(Set<ConstraintViolation<?>> violations) {
        Map<String, String> errors = new HashMap<>();
        violations.forEach(e -> errors.put(((PathImpl) e.getPropertyPath()).getLeafNode().asString(), e.getMessage()));
        return errors;
    }

    private Map<String, String> getValidationErrors(List<FieldError> fieldErrors) {
        Map<String, String> errors = new HashMap<>();
        fieldErrors.forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
        return errors;
    }
}
