package com.company.util;

import com.company.model.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public class ResponseBaseUtil {

    public ResponseBaseUtil() {
    }

    public static ResponseEntity<ResponseDto<?>> buildResponse(Object data, String message, boolean success, int status) {
        ResponseDto<?> responseDto = convertResponseDto(data, message, success, status);
        return ResponseEntity.status(status).body(responseDto);
    }

    private static ResponseDto<?> convertResponseDto(Object data, String message, Boolean success, int code) {
        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(success);
        responseDto.setData(data);
        responseDto.setCode(code);
        responseDto.setMessage(message);
        return responseDto;
    }
}
