package com.company.service;

import com.company.model.form.DepartmentForm;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {

    ResponseEntity<?> add(DepartmentForm departmentForm);

    ResponseEntity<?> update(int id, DepartmentForm departmentForm);

    ResponseEntity<?> delete(int permissionId);

    ResponseEntity<?> get(int permissionId);

    ResponseEntity<?> list();
}
