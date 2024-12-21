package com.company.controller;

import com.company.model.form.BaseForm;
import com.company.model.form.UserForm;
import com.company.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@Hidden
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> doRegister(@Valid @RequestBody UserForm userForm) {
        log.info("Received create user request {}", userForm);
        return userService.doRegister(userForm);
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@Valid @RequestBody BaseForm userLoginForm) {
        log.info("Received login user request {}", userLoginForm);
        return userService.doLogin(userLoginForm);
    }


}
