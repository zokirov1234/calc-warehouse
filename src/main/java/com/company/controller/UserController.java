package com.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.company.model.form.UserForm;
import com.company.model.form.UserListForm;
import com.company.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Slf4j
@Tag(name = "User")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            description = "Get users",
            summary = "get endpoint of user"
    )
    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(
            @PathVariable("id") int id
    ) {
        log.info("Get user by id {}", id);
        return userService.getUserById(id);
    }

    @Operation(
            description = "Update user",
            summary = "update endpoint of user"
    )
    @PreAuthorize("hasAuthority('admin:update')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") int id,
            @Valid @RequestBody UserForm userForm) {
        log.info("User updating with id {}", id);
        return userService.updateUserById(id, userForm);
    }

    @Operation(
            description = "Get list users",
            summary = "get endpoint of users"
    )
    @PreAuthorize("hasAuthority('admin:read')")
    @PostMapping("/list")
    public ResponseEntity<?> getList(
            @RequestBody UserListForm userListForm
    ) {
        return userService.listUsers(userListForm);
    }


    @Operation(
            description = "Delete user",
            summary = "delete endpoint of users"
    )
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable("id") int id
    ) {
        log.info("User deleting by id {}", id);
        return userService.deleteUserById(id);
    }


}
