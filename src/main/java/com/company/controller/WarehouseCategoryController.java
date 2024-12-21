package com.company.controller;

import com.company.model.form.WarehouseCategoryForm;
import com.company.model.form.WarehouseCategoryUserAccessForm;
import com.company.service.WarehouseCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/warehouse-category")
@AllArgsConstructor
public class WarehouseCategoryController {

    private final WarehouseCategoryService warehouseCategoryService;

    @PostMapping("/add")
    public ResponseEntity<?> createWarehouseCategory(
            @RequestBody WarehouseCategoryForm warehouseCategoryForm
    ) {
        log.info("Received create warehouse category request {}", warehouseCategoryForm);
        return warehouseCategoryService.addWarehouseCategory(warehouseCategoryForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWarehouseCategory(
            @PathVariable("id") int id,
            @RequestBody WarehouseCategoryForm warehouseCategoryForm
    ) {
        log.info("Received update warehouse category request {}", warehouseCategoryForm);
        return warehouseCategoryService.updateWarehouseCategory(warehouseCategoryForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWarehouseCategory(
            @PathVariable("id") int id
    ) {
        log.info("Received delete warehouse category request {}", id);
        return warehouseCategoryService.deleteWarehouseCategory(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listWarehouseCategories() {
        log.info("Received list warehouse category request");
        return warehouseCategoryService.listWarehouseCategories();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getWarehouseCategoryById(
            @PathVariable("id") int id
    ) {
        log.info("Received get warehouse category request {}", id);
        return warehouseCategoryService.getWarehouseCategory(id);
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> addWarehouseCategoryUserAccess(
            @RequestBody WarehouseCategoryUserAccessForm warehouseCategoryUserAccessForm
    ) {
        log.info("Received create warehouse category user access request {}", warehouseCategoryUserAccessForm);
        return warehouseCategoryService.addWarehouseCategoryUserAccess(warehouseCategoryUserAccessForm);
    }

}
