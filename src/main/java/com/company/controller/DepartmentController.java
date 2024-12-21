package com.company.controller;

import com.company.model.form.DepartmentForm;
import com.company.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody DepartmentForm departmentForm
    ) {
        log.info("Received add department : {}", departmentForm);
        return departmentService.add(departmentForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody DepartmentForm departmentForm
    ) {
        log.info("Received update department with id: {}", id);
        return departmentService.update(id, departmentForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Received delete department with id: {}", id);
        return departmentService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Received get department with id: {}", id);
        return departmentService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("Received list department");
        return departmentService.list();
    }

}
