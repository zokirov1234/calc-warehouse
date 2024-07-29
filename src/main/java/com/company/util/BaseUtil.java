package com.company.util;

import com.company.model.dto.ResponseDto;
import com.company.model.dto.UserDto;
import com.company.model.form.UserForm;
import org.springframework.stereotype.Service;

@Service
public class BaseUtil {

    public static String baseURL = "https://easy-eats.uz/images/";

    public UserDto formToDto(UserForm userForm, int id) {
        return UserDto.builder()
                .id(id)
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .username(userForm.getUsername())
                .role(userForm.getRole())
                .build();
    }

    public ResponseDto<?> convertResponseDto(Object data, String message, Boolean success, int code) {


        ResponseDto<Object> responseDto = new ResponseDto<>();
        responseDto.setSuccess(success);
        responseDto.setData(data);
        responseDto.setCode(code);
        responseDto.setMessage(message);
        return responseDto;
    }
}
