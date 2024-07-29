package com.company.controller;

import com.company.model.form.WarehouseForm;
import com.company.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/warehouse")
@AllArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("/add")
    public ResponseEntity<?> createWarehouse(
            @RequestBody WarehouseForm warehouseForm
    ) {
        log.info("Received create warehouse request {}", warehouseForm);
        return warehouseService.addWarehouse(warehouseForm);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWarehouse(
            @PathVariable("id") int id,
            @RequestBody WarehouseForm warehouseForm
    ) {
        log.info("Received update warehouse request {}", warehouseForm);
        return warehouseService.updateWarehouse(warehouseForm, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWarehouse(
            @PathVariable("id") int id
    ) {
        log.info("Received delete warehouse request {}", id);
        return warehouseService.deleteWarehouse(id);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listWarehouses() {
        log.info("Received list warehouse request");
        return warehouseService.listWarehouses();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getWarehouseById(
            @PathVariable("id") int id
    ) {
        log.info("Received get warehouse request {}", id);
        return warehouseService.getWarehouse(id);
    }

}
