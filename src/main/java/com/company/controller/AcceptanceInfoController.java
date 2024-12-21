package com.company.controller;

import com.company.model.form.AcceptanceInfoCreateForm;
import com.company.service.AcceptanceInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/acceptance-info")
@AllArgsConstructor
public class AcceptanceInfoController {

    private final AcceptanceInfoService acceptanceInfoService;

    @PostMapping("/add")
    public ResponseEntity<?> createAcceptanceInfo(
            @RequestBody AcceptanceInfoCreateForm acceptanceInfoCreateForm
    ) {
        log.info("Received create acceptance info request {}", acceptanceInfoCreateForm);
        return acceptanceInfoService.createAcceptanceInfo(acceptanceInfoCreateForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAcceptanceInfo(
            @PathVariable("id") int id,
            @RequestBody AcceptanceInfoCreateForm acceptanceInfoCreateForm
    ) {
        log.info("Received update acceptance info request {}", acceptanceInfoCreateForm);
        return acceptanceInfoService.updateAcceptanceInfo(acceptanceInfoCreateForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAcceptanceInfo(
            @PathVariable("id") int id
    ) {
        log.info("Received delete acceptance info request {}", id);
        return acceptanceInfoService.deleteAcceptanceInfo(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listAcceptanceInfo() {
        log.info("Received list acceptance info request");
        return acceptanceInfoService.listAcceptanceInfo();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAcceptanceInfoById(
            @PathVariable("id") int id
    ) {
        log.info("Received get acceptance info request {}", id);
        return acceptanceInfoService.getAcceptanceInfoById(id);
    }
}
