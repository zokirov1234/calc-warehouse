package com.company.controller;

import com.company.model.form.RolesForm;
import com.company.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody RolesForm rolesForm
    ) {
        log.info("Received add role : {}", rolesForm);
        return roleService.add(rolesForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody RolesForm rolesForm
    ) {
        log.info("Received update role with id: {}", id);
        return roleService.update(id, rolesForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Received delete role with id: {}", id);
        return roleService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Received get role with id: {}", id);
        return roleService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("Received list roles");
        return roleService.list();
    }

}
