package com.company.service;

import com.company.model.form.EducationTypeForm;
import org.springframework.http.ResponseEntity;

public interface EducationTypeService {


    ResponseEntity<?> add(EducationTypeForm educationTypeForm);

    ResponseEntity<?> update(int id, EducationTypeForm educationTypeForm);

    ResponseEntity<?> delete(int educationTypeId);

    ResponseEntity<?> get(int educationTypeId);

    ResponseEntity<?> list();

}
