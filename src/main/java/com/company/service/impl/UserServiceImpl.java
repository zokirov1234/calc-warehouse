package com.company.service.impl;

import com.company.config.JwtService;
import com.company.enums.Roles;
import com.company.mapper.UserMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.dto.UserDto;
import com.company.model.entity.UserEntity;
import com.company.model.form.BaseForm;
import com.company.model.form.UserForm;
import com.company.model.form.UserListForm;
import com.company.repository.UserRepository;
import com.company.service.UserService;
import com.company.service.general.CommonService;
import com.company.util.BaseUtil;
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

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /*
     * if password == null
     *  set old password else new password
     * */

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final BaseUtil baseUtil;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserMapper userMapper;

    private final CommonService commonService;


    @Override
    public ResponseEntity<?> doRegister(UserForm userForm) {
        ResponseDto<?> response;

        UserEntity user;
        try {

            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));

            user = userRepository.save(userMapper.UserFormToUserEntity(userForm));

        } catch (Exception exception) {
            log.warn("User creation failed, Item found exception");
            response = baseUtil.convertResponseDto(null, "Item found exception", false, 500);
            return ResponseEntity.status(500).body(response);
        }

        log.info("User saved successfully");
        response = baseUtil.convertResponseDto(baseUtil.formToDto(userForm, user.getId()), "success", true, 200);
        return ResponseEntity.status(200).body(response);
    }


    @Override
    public ResponseEntity<?> doLogin(BaseForm baseForm) {
        String token = "";

        ResponseDto<?> response;

        Optional<UserEntity> username
                = userRepository.findByUsername(baseForm.getField());

        String dtoUsername = "";
        String role = "";

        if (username.isEmpty()) {
            log.warn("There is no user with this username");
            response = baseUtil.convertResponseDto(null, "Username or password wrong", false, 500);
            return ResponseEntity.status(500).body(response);
        }


        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(baseForm.getField(), baseForm.getObject()));

        if (!username.get().getState()) {
            log.warn("User has been deleted");

            response = baseUtil.convertResponseDto(null, "User is deleted", false, 403);
            return ResponseEntity.status(403).body(response);
        }

        if (!auth.isAuthenticated()) {
            log.warn("There is no user with this username");
            response = baseUtil.convertResponseDto(null, "Username or password wrong", false, 500);
            return ResponseEntity.status(500).body(response);
        } else {
            token = jwtService.generateToken(baseForm.getField());
            dtoUsername = username.get().getUsername();
            role = username.get().getRoles().toString();
        }


        log.info("logged success");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("username", dtoUsername);
        map.put("role", role);

        response = baseUtil.convertResponseDto(map, "success", true, 200);
        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<ResponseDto<UserDto>> getUserById(Integer id) {
        ResponseDto<?> response;

        response = baseUtil.convertResponseDto(userMapper.UserToUserDTO(userRepository.findUserById(id)), "success", true, 200);

        return ResponseEntity.status(200).body((ResponseDto<UserDto>) response);

    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public ResponseEntity<?> updateUserById(int id, UserForm userForm) {
        ResponseDto<?> response;

        try {
            UserEntity user = userRepository.findUserById(id);
            if (ObjectUtils.isEmpty(userForm.getPassword())){
                userForm.setPassword(user.getPassword());
            }else {
                userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
            }
            userRepository.updateUser(userForm.getFirstName(),
                    userForm.getLastName(), userForm.getUsername(),
                    userForm.getPassword(),
                    Roles.valueOf(userForm.getRole()), id);
            response = baseUtil.convertResponseDto(null, "User updated successfully", true, 200);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response = baseUtil.convertResponseDto(null, "Something went wrong while updating user", false, 500);
            return ResponseEntity.status(500).body(response);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserById(int id) {
        ResponseDto<?> response;

        try {

            String newUsername = commonService.updateState(userRepository.findUserById(id).getUsername());

            userRepository.deleteUserById(newUsername, id);
            log.info("User deleted successfully");
            response = baseUtil.convertResponseDto(null, "success", true, 200);
            return ResponseEntity.status(200).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Something went wrong while deleting user {}", id);
            response = baseUtil.convertResponseDto(null, "Something went wrong while deleting user", false, 500);
            return ResponseEntity.status(500).body(response);
        }
    }

    @Override
    public ResponseEntity<?> listUsers(UserListForm userListForm) {
        ResponseDto<?> response;

        log.info("Getting list of users");

        List<UserEntity> userList
                = userRepository.findUserList(userListForm.getUsername(), userListForm.getFirstName(), userListForm.getLastName());

        int start = userListForm.getPage() * userListForm.getSize();
        int end = userListForm.getSize() * (userListForm.getPage() + 1);


        if (end > userList.size()) {

            if (start > userList.size()) {
                start = 0;
            }

            end = userList.size();
        }

        List<UserDto> dtoList = new ArrayList<>();

        for (UserEntity user : userList.subList(start, end)) {
            dtoList.add(userMapper.UserToUserDTO(user));
        }

        Map<String, Object> res = new HashMap<>();
        res.put("list", dtoList);
        res.put("count", dtoList.size());
        response = baseUtil.convertResponseDto(res, "success", true, 200);
        return ResponseEntity.status(200).body(response);
    }

}
