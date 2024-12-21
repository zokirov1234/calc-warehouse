package com.company.service;

import com.company.model.form.PermissionForm;
import org.springframework.http.ResponseEntity;

public interface PermissionService {

    ResponseEntity<?> add(PermissionForm permissionForm);

    ResponseEntity<?> update(int id, PermissionForm permissionForm);

    ResponseEntity<?> delete(int permissionId);

    ResponseEntity<?> get(int permissionId);

    ResponseEntity<?> list();
}
