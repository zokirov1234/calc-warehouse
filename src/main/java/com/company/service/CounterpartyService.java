package com.company.service;



import com.company.model.form.CounterpartyForm;
import org.springframework.http.ResponseEntity;

public interface CounterpartyService {

    ResponseEntity<?> addCounterparty(CounterpartyForm counterpartyForm);

    ResponseEntity<?> updateCounterparty(CounterpartyForm counterpartyForm, int id);

    ResponseEntity<?> deleteCounterparty(int id);

    ResponseEntity<?> listCounterparties();

    ResponseEntity<?> getCounterparty(int id);
}
