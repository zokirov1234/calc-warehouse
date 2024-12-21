package com.company.service.impl;

import com.company.config.JwtService;
import com.company.enums.Roles;
import com.company.mapper.UserMapper;
import com.company.model.dto.UserDto;
import com.company.model.entity.UserEntity;
import com.company.model.form.BaseForm;
import com.company.model.form.UserCreateForm;
import com.company.model.form.UserForm;
import com.company.model.form.UserListForm;
import com.company.repository.UserRepository;
import com.company.service.UserService;
import com.company.service.general.CommonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final CommonService commonService;

    @Override
    public ResponseEntity<?> doRegister(UserForm userForm) {
        try {
            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
            UserEntity user = userRepository.save(userMapper.UserFormToUserEntity(userForm));
            log.info("User saved successfully");
            return buildResponse(formToDto(userForm, user.getId()), "User registered successfully", true, 200);
        } catch (Exception exception) {
            log.warn("User creation failed, Item found exception");
            return buildResponse(null, "Item found exception", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> doLogin(BaseForm baseForm) {
        Optional<UserEntity> username = userRepository.findByUsername(baseForm.getField());
        if (username.isEmpty()) {
            log.warn("No user found with this username");
            return buildResponse(null, "Username or password wrong", false, 500);
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(baseForm.getField(), baseForm.getObject()));

        if (!username.get().getState()) {
            log.warn("User is deleted");
            return buildResponse(null, "User is deleted", false, 403);
        }
        if (!auth.isAuthenticated()) {
            log.warn("Authentication failed for user {}", baseForm.getField());
            return buildResponse(null, "Username or password wrong", false, 500);
        } else {
            String token = jwtService.generateToken(baseForm.getField());
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("username", username.get().getUsername());
            map.put("role", username.get().getRoles().toString());
            log.info("Login successful");
            return buildResponse(map, "Login successful", true, 200);
        }
    }

    @Override
    public ResponseEntity<?> getUserById(Integer id) {
        return buildResponse(userMapper.UserToUserDTO(userRepository.findUserById(id)), "User found", true, 200);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public ResponseEntity<?> updateUserById(int id, UserForm userForm) {
        try {
            UserEntity user = userRepository.findUserById(id);
            if (ObjectUtils.isEmpty(userForm.getPassword())) {
                userForm.setPassword(user.getPassword());
            } else {
                userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
            }
            userRepository.updateUser(userForm.getFirstName(), userForm.getLastName(), userForm.getUsername(),
                    userForm.getPassword(), Roles.valueOf(userForm.getRole()), id);
            return buildResponse(null, "User updated successfully", true, 200);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            return buildResponse(null, "Error updating user", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteUserById(int id) {
        try {
            String newUsername = commonService.updateState(userRepository.findUserById(id).getUsername());
            userRepository.deleteUserById(newUsername, id);
            log.info("User deleted successfully");
            return buildResponse(null, "User deleted successfully", true, 200);
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage());
            return buildResponse(null, "Error deleting user", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listUsers(UserListForm userListForm) {
        log.info("Getting list of users");
        List<UserEntity> userList = userRepository.findUserList(userListForm.getUsername(), userListForm.getFirstName(), userListForm.getLastName());
        int start = userListForm.getPage() * userListForm.getSize();
        int end = userListForm.getSize() * (userListForm.getPage() + 1);
        if (end > userList.size()) {
            if (start > userList.size()) start = 0;
            end = userList.size();
        }
        List<UserDto> listDto = userList.subList(start, end).stream()
                .map(userMapper::UserToUserDTO)
                .toList();

        Map<String, Object> res = new HashMap<>();
        res.put("list", listDto);
        res.put("count", listDto.size());
        return buildResponse(res, "Users list retrieved successfully", true, 200);
    }

    @Override
    public ResponseEntity<?> createUser(UserCreateForm userCreateForm) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(userCreateForm.getUsername());
        if (userOptional.isPresent()) {
            log.warn("Username already exists {}", userCreateForm.getUsername());
            return buildResponse(null, "Username already exists", false, 400);
        }
        try {
            userRepository.save(createUserBuilder(userCreateForm));
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return buildResponse(null, "Error creating user", false, 500);
        }
        log.info("User created successfully {}", userCreateForm.getUsername());
        return buildResponse(null, "User created successfully", true, 200);
    }

    private UserEntity createUserBuilder(UserCreateForm userCreateForm) {
        return UserEntity.builder()
                .username(userCreateForm.getUsername())
                .firstName(userCreateForm.getFirstName())
                .lastName(userCreateForm.getLastName())
                .middleName(userCreateForm.getMiddleName())
                .isMale(userCreateForm.isMale())
                .attachId(userCreateForm.getAttachId())
                .isWorking(userCreateForm.isWorking())
                .dateOfBirth(userCreateForm.getDateOfBirth())
                .educationTypeId(userCreateForm.getEducationTypeId())
                .salaryType(userCreateForm.getSalaryType())
                .password(userCreateForm.getPassword())
                .phoneNumber(userCreateForm.getPhoneNumber())
                .build();
    }

    private UserDto formToDto(UserForm userForm, int id) {
        return UserDto.builder()
                .id(id)
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .username(userForm.getUsername())
                .role(userForm.getRole())
                .build();
    }
}
