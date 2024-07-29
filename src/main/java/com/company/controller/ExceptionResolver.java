package com.company.controller;

import lombok.extern.slf4j.Slf4j;
import com.company.model.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleSecurityException(Exception ex) {

        ResponseDto<?> responseDto = new ResponseDto<>();

        if (ex instanceof BadCredentialsException) {
            responseDto.setCode(401);
        }

        log.error("Error occurred by cause : {}, message : {}", ex.getCause(), ex.getMessage());

        responseDto.setMessage(ex.getMessage());
        responseDto.setCode(403);
        responseDto.setSuccess(Boolean.FALSE);
        responseDto.setData(null);
        return ResponseEntity.ok().body(responseDto);
    }
}
