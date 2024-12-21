package com.company.service;

import com.company.model.form.RolesForm;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    ResponseEntity<?> add(RolesForm rolesForm);

    ResponseEntity<?> update(int id, RolesForm rolesForm);

    ResponseEntity<?> delete(int clientGroupId);

    ResponseEntity<?> get(int clientGroupId);

    ResponseEntity<?> list();

}
