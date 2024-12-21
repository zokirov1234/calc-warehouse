package com.company.service;

import com.company.model.form.WarehouseSubCategoryForm;
import org.springframework.http.ResponseEntity;

public interface WarehouseSubCategoryService {

    ResponseEntity<?> addWarehouseSubCategory(WarehouseSubCategoryForm warehouseSubCategoryForm);

    ResponseEntity<?> updateWarehouseSubCategory(WarehouseSubCategoryForm warehouseSubCategoryForm, int id);

    ResponseEntity<?> deleteWarehouseSubCategory(int id);

    ResponseEntity<?> listWarehouseSubCategories();

    ResponseEntity<?> getWarehouseSubCategory(int id);
}
