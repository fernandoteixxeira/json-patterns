package com.github.fernando.jsonpatterns.entrypoint.api.errorhandler;

import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Exception controller.
 */
@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception exception) {
        ApiError apiError = new ApiError(exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
                                                                         ) {
        String errorMessage = MessageFormat.format("{0} parameter is missing", exception.getParameterName());
        val error = new Error("path", exception.getParameterName(), null, errorMessage);
        val apiError = new ApiError("some fields contain errors", List.of(error));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<ApiError> constraintViolationException(ConstraintViolationException exception) {
        val errors = exception.getConstraintViolations().stream()
            .map(error -> new Error("path", error.getPropertyPath().toString(), error.getInvalidValue().toString(), error.getMessage()))
            .collect(Collectors.toList());
        ApiError apiError = new ApiError("some fields contain errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
                                                                 ) {
        val attributeErrors = exception.getBindingResult().getFieldErrors()
            .stream()
            .map(error -> new Error("attribute", error.getField(), String.valueOf(error.getRejectedValue()), error.getDefaultMessage()))
            .collect(Collectors.toList());

        val validationErrors = exception.getBindingResult().getGlobalErrors()
            .stream()
            .map(error -> new Error("validation", null, null, error.getDefaultMessage()))
            .collect(Collectors.toList());

        val errorList = Stream.of(attributeErrors, validationErrors).flatMap(List::stream).collect(Collectors.toList());

        val apiError = new ApiError("some fields contain errors", errorList);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        val apiError = new ApiError(ex.getMostSpecificCause().getMessage(), null);
        return ResponseEntity.status(status).body(apiError);
    }

}