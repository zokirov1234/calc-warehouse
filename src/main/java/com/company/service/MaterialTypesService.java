package com.company.service;

import com.company.model.form.MaterialTypesForm;
import org.springframework.http.ResponseEntity;

public interface MaterialTypesService {

    ResponseEntity<?> add(MaterialTypesForm materialTypesForm);

    ResponseEntity<?> update(int id, MaterialTypesForm materialTypesForm);

    ResponseEntity<?> delete(int materialTypesId);

    ResponseEntity<?> get(int materialTypesId);

    ResponseEntity<?> list();

}
