package com.company.controller;

import com.company.model.form.AcceptanceCreateForm;
import com.company.service.AcceptanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/acceptance")
@AllArgsConstructor
public class AcceptanceController {

    private final AcceptanceService acceptanceService;

    @PostMapping("/add")
    public ResponseEntity<?> createAcceptance(
            @RequestBody AcceptanceCreateForm acceptanceCreateForm
    ) {
        log.info("Received create acceptance request {}", acceptanceCreateForm);
        return acceptanceService.createAcceptance(acceptanceCreateForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAcceptance(
            @PathVariable("id") int id,
            @RequestBody AcceptanceCreateForm acceptanceForm
    ) {
        log.info("Received update acceptance info request {}", acceptanceForm);
        return acceptanceService.updateAcceptance(acceptanceForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAcceptance(
            @PathVariable("id") int id
    ) {
        log.info("Received delete acceptance request {}", id);
        return acceptanceService.deleteAcceptance(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAcceptance() {
        log.info("Received list acceptance request");
        return acceptanceService.listAcceptance();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAcceptanceById(
            @PathVariable("id") int id
    ) {
        log.info("Received get acceptance request {}", id);
        return acceptanceService.getAcceptanceById(id);
    }
}
