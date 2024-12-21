package com.company.service;

import com.company.model.form.WorkshopForm;
import org.springframework.http.ResponseEntity;

public interface WorkshopService {

    ResponseEntity<?> add(WorkshopForm workshopForm);

    ResponseEntity<?> update(int id, WorkshopForm workshopForm);

    ResponseEntity<?> delete(int clientGroupId);

    ResponseEntity<?> get(int clientGroupId);

    ResponseEntity<?> list();
}
