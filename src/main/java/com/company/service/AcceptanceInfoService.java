package com.company.service;

import com.company.model.form.AcceptanceInfoCreateForm;
import org.springframework.http.ResponseEntity;

public interface AcceptanceInfoService {

    ResponseEntity<?> createAcceptanceInfo(AcceptanceInfoCreateForm acceptanceInfoCreateForm);

    ResponseEntity<?> updateAcceptanceInfo(AcceptanceInfoCreateForm acceptanceInfoCreateForm, int id);

    ResponseEntity<?> deleteAcceptanceInfo(int id);

    ResponseEntity<?> listAcceptanceInfo();

    ResponseEntity<?> getAcceptanceInfoById(int id);
}
