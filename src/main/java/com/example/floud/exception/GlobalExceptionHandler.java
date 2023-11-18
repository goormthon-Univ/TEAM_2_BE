package com.example.floud.exception;

import com.example.floud.dto.response.user.SignupErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<SignupErrorResponseDto> handleCustomException(CustomException ex) {
        SignupErrorResponseDto errorResponseDto = new SignupErrorResponseDto(ex.getStatus().value(), ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, ex.getStatus());
    }
}
