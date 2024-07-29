package com.company.service;


import com.company.model.form.MaterialForm;
import org.springframework.http.ResponseEntity;

public interface MaterialService {

    ResponseEntity<?> addMaterial(MaterialForm materialForm);

    ResponseEntity<?> updateMaterial(MaterialForm materialForm, int id);

    ResponseEntity<?> deleteMaterial(int id);

    ResponseEntity<?> listMaterial();

    ResponseEntity<?> getMaterial(int id);
}
