package com.company.controller;

import com.company.model.form.ClientGroupForm;
import com.company.service.ClientGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/client-group")
public class ClientGroupController {

    private final ClientGroupService clientGroupService;

    @PostMapping("/add")
    public ResponseEntity<?> add(
            @RequestBody ClientGroupForm clientGroupForm
    ) {
        log.info("Received add client group: {}", clientGroupForm);
        return clientGroupService.add(clientGroupForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody ClientGroupForm clientGroupForm
    ) {
        log.info("Received update client group with id: {}", id);
        return clientGroupService.update(id, clientGroupForm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {
        log.info("Received delete client group with id: {}", id);
        return clientGroupService.delete(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(
            @PathVariable("id") int id
    ) {
        log.info("Received get client group with id: {}", id);
        return clientGroupService.get(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("Received list client group");
        return clientGroupService.list();
    }

}
