package com.company.service;

import com.company.model.form.CurrenciesForm;
import org.springframework.http.ResponseEntity;

public interface CurrenciesService {


    ResponseEntity<?> add(CurrenciesForm currenciesForm);

    ResponseEntity<?> update(CurrenciesForm currenciesForm);

    ResponseEntity<?> delete(int currenciesId);

    ResponseEntity<?> get(int currenciesId);

    ResponseEntity<?> list();

}
