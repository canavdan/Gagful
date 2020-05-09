package com.gagful.exception;

import com.gagful.base.BaseResponse;
import com.gagful.controller.response.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {


    private final ResponseUtil responseUtil;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse> handleUserNotFoundException(
            UserNotFoundException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<BaseResponse> handlePostNotFoundException(
            PostNotFoundException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<BaseResponse> handlePostNotFoundException(
            PasswordNotMatchException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        return responseUtil.badRequestResponse(errors.toString(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String columnName = violation.getPropertyPath().toString().substring(violation.getPropertyPath().toString().lastIndexOf('.') + 1);
            errors.add(columnName + " " + violation.getMessage());
        }
        return responseUtil.badRequestResponse(errors.toString(), errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleUniqueException(
            DataIntegrityViolationException ex) {
        List<String> errors = new ArrayList<>();
        if (ex.getMessage().contains("unique")) {
            if (ex.getMessage().contains("username")) {
                errors.add("Username is exist.");
            } else if (ex.getMessage().contains("categoryName")) {
                errors.add("Category name is exist.");
            } else {
                errors.add("Email is exist.");
            }

        }
        return responseUtil.badRequestResponse(errors.toString(), errors);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<BaseResponse> handleUniqueException(
            InternalAuthenticationServiceException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(TokenMalformedException.class)
    public ResponseEntity<BaseResponse> handleTokenMalformedException(
            TokenMalformedException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(TokenIsEmptyException.class)
    public ResponseEntity<BaseResponse> handleTokenIsEmptyException(
            TokenIsEmptyException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }

    @ExceptionHandler(InvalidUserCredentialsException.class)
    public ResponseEntity<BaseResponse> handleInvalidUserCredentialsException(
            InvalidUserCredentialsException ex) {
        return responseUtil.badRequestResponse(ex.getMessage());
    }
}