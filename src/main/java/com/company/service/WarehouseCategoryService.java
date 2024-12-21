package com.company.service;

import com.company.model.form.WarehouseCategoryForm;
import com.company.model.form.WarehouseCategoryUserAccessForm;
import org.springframework.http.ResponseEntity;

public interface WarehouseCategoryService {

    ResponseEntity<?> addWarehouseCategory(WarehouseCategoryForm warehouseForm);

    ResponseEntity<?> updateWarehouseCategory(WarehouseCategoryForm warehouseForm, int id);

    ResponseEntity<?> deleteWarehouseCategory(int id);

    ResponseEntity<?> listWarehouseCategories();

    ResponseEntity<?> getWarehouseCategory(int id);

    ResponseEntity<?> addWarehouseCategoryUserAccess(WarehouseCategoryUserAccessForm warehouseCategoryUserAccessForm);
}
