package com.company.controller;

import com.company.model.form.CurrenciesForm;
import com.company.service.CurrenciesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/currencies")
public class CurrenciesController {

    private final CurrenciesService currenciesService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody CurrenciesForm currenciesForm
    ) {
        log.info("Add currencies: {}", currenciesForm);
        return currenciesService.add(currenciesForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @RequestBody CurrenciesForm currenciesForm
    ) {
        log.info("Update currencies with id: {}", currenciesForm);
        return currenciesService.update(currenciesForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Delete currencies with id: {}", id);
        return currenciesService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Get currencies with id: {}", id);
        return currenciesService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("List currencies");
        return currenciesService.list();
    }

}
