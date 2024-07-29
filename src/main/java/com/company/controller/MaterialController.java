package com.company.controller;

import com.company.model.form.MaterialForm;
import com.company.service.MaterialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/material")
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping("/add")
    public ResponseEntity<?> createMaterial(
            @RequestBody MaterialForm materialForm
    ) {
        log.info("Received create material request {}", materialForm);
        return materialService.addMaterial(materialForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaterial(
            @PathVariable("id") int id,
            @RequestBody MaterialForm materialForm
    ) {
        log.info("Received update material request {}", materialForm);
        return materialService.updateMaterial(materialForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMaterial(
            @PathVariable("id") int id
    ) {
        log.info("Received delete material request {}", id);
        return materialService.deleteMaterial(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listMaterials() {
        log.info("Received list material request");
        return materialService.listMaterial();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMaterialById(
            @PathVariable("id") int id
    ) {
        log.info("Received get material request {}", id);
        return materialService.getMaterial(id);
    }

}
