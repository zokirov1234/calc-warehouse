package com.company.controller;

import com.company.model.form.CounterpartyForm;
import com.company.service.CounterpartyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/counterparty")
@AllArgsConstructor
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    @PostMapping("/add")
    public ResponseEntity<?> createCounterparty(
            @RequestBody CounterpartyForm counterpartyForm
    ) {
        log.info("Received create counterparty request {}", counterpartyForm);
        return counterpartyService.addCounterparty(counterpartyForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCounterparty(
            @PathVariable("id") int id,
            @RequestBody CounterpartyForm counterpartyForm
    ) {
        log.info("Received update counterparty request {}", counterpartyForm);
        return counterpartyService.updateCounterparty(counterpartyForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCounterparty(
            @PathVariable("id") int id
    ) {
        log.info("Received delete counterparty request {}", id);
        return counterpartyService.deleteCounterparty(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listCounterparties() {
        log.info("Received list counterparty request");
        return counterpartyService.listCounterparties();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCounterpartyById(
            @PathVariable("id") int id
    ) {
        log.info("Received get counterparty request {}", id);
        return counterpartyService.getCounterparty(id);
    }

}
