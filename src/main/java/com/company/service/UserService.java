package com.company.service;

import com.company.model.form.BaseForm;
import com.company.model.form.UserCreateForm;
import com.company.model.form.UserForm;
import com.company.model.form.UserListForm;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> doRegister(UserForm userForm);

    ResponseEntity<?> doLogin(BaseForm baseForm);

    ResponseEntity<?> getUserById(Integer id);

    ResponseEntity<?> updateUserById(int id, UserForm userForm);

    ResponseEntity<?> deleteUserById(int id);

    ResponseEntity<?> listUsers(UserListForm userListForm);

    ResponseEntity<?> createUser(UserCreateForm userCreateForm);
}