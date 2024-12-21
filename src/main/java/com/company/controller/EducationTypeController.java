package com.company.controller;

import com.company.model.form.EducationTypeForm;
import com.company.service.EducationTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/education/type")
public class EducationTypeController {

    private final EducationTypeService educationTypeService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody EducationTypeForm educationTypeForm
    ) {
        log.info("Received add educationType: {}", educationTypeForm);
        return educationTypeService.add(educationTypeForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody EducationTypeForm educationTypeForm
    ) {
        log.info("Received update educationType with id: {}", id);
        return educationTypeService.update(id, educationTypeForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Received delete educationType with id: {}", id);
        return educationTypeService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Received get educationType with id: {}", id);
        return educationTypeService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("Received list educationType");
        return educationTypeService.list();
    }

}
