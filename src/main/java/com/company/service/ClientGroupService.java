package com.company.service;

import com.company.model.form.ClientGroupForm;
import org.springframework.http.ResponseEntity;

public interface ClientGroupService {


    ResponseEntity<?> add(ClientGroupForm clientGroupForm);

    ResponseEntity<?> update(ClientGroupForm clientGroupForm);

    ResponseEntity<?> delete(int clientGroupId);

    ResponseEntity<?> get(int clientGroupId);

    ResponseEntity<?> list();

}
