package com.company.controller;

import com.company.model.form.PermissionForm;
import com.company.service.PermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody PermissionForm permissionForm
    ) {
        log.info("Received add permission : {}", permissionForm);
        return permissionService.add(permissionForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody PermissionForm permissionForm
    ) {
        log.info("Received update permission with id: {}", id);
        return permissionService.update(id, permissionForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Received delete permission with id: {}", id);
        return permissionService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Received get permission with id: {}", id);
        return permissionService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("Received list permissions");
        return permissionService.list();
    }

}
