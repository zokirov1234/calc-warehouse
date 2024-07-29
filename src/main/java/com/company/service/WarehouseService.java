package com.company.service;

import com.company.model.form.WarehouseForm;
import org.springframework.http.ResponseEntity;

public interface WarehouseService {

    ResponseEntity<?> addWarehouse(WarehouseForm warehouseForm);

    ResponseEntity<?> updateWarehouse(WarehouseForm warehouseForm, int id);

    ResponseEntity<?> deleteWarehouse(int id);

    ResponseEntity<?> listWarehouses();

    ResponseEntity<?> getWarehouse(int id);
}
