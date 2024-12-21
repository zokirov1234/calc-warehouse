package com.company.service;

import com.company.model.form.AcceptanceCreateForm;
import org.springframework.http.ResponseEntity;

public interface AcceptanceService {

    ResponseEntity<?> createAcceptance(AcceptanceCreateForm acceptanceCreateForm);

    ResponseEntity<?> updateAcceptance(AcceptanceCreateForm acceptanceCreateForm, int id);

    ResponseEntity<?> deleteAcceptance(int id);

    ResponseEntity<?> listAcceptance();

    ResponseEntity<?> getAcceptanceById(int id);
}
