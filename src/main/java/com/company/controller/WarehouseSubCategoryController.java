package com.company.controller;

import com.company.model.form.WarehouseSubCategoryForm;
import com.company.service.WarehouseSubCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/warehouse/sub-category")
@AllArgsConstructor
public class WarehouseSubCategoryController {

    private final WarehouseSubCategoryService warehouseSubCategoryService;

    @PostMapping("/add")
    public ResponseEntity<?> createWarehouseSubCategory(
            @RequestBody WarehouseSubCategoryForm warehouseSubCategoryForm
    ) {
        log.info("Received create warehouse sub category request {}", warehouseSubCategoryForm);
        return warehouseSubCategoryService.addWarehouseSubCategory(warehouseSubCategoryForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWarehouseSubCategory(
            @PathVariable("id") int id,
            @RequestBody WarehouseSubCategoryForm warehouseSubCategoryForm
    ) {
        log.info("Received update warehouse request {}", warehouseSubCategoryForm);
        return warehouseSubCategoryService.updateWarehouseSubCategory(warehouseSubCategoryForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWarehouseSubCategory(
            @PathVariable("id") int id
    ) {
        log.info("Received delete warehouse sub category request {}", id);
        return warehouseSubCategoryService.deleteWarehouseSubCategory(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listWarehouseSubCategories() {
        log.info("Received list warehouse sub category request");
        return warehouseSubCategoryService.listWarehouseSubCategories();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getWarehouseSubCategoryById(
            @PathVariable("id") int id
    ) {
        log.info("Received get warehouse sub category request {}", id);
        return warehouseSubCategoryService.getWarehouseSubCategory(id);
    }

}
