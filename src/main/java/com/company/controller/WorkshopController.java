package com.company.controller;

import com.company.model.form.WorkshopForm;
import com.company.service.WorkshopService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/workshop")
public class WorkshopController {

    private final WorkshopService workshopService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody WorkshopForm workshopForm
    ) {
        log.info("Received add workshop: {}", workshopForm);
        return workshopService.add(workshopForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody WorkshopForm workshopForm
    ) {
        log.info("Received update workshop with id: {}", id);
        return workshopService.update(id, workshopForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Received delete workshop with id: {}", id);
        return workshopService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Received get workshop with id: {}", id);
        return workshopService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("Received list workshop");
        return workshopService.list();
    }

}
