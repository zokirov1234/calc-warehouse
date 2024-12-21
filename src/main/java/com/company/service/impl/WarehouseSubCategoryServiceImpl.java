package com.company.service.impl;

import com.company.mapper.WarehouseSubCategoryMapper;
import com.company.model.entity.WarehouseSubCategory;
import com.company.model.form.WarehouseSubCategoryForm;
import com.company.repository.WarehouseSubCategoryRepository;
import com.company.service.WarehouseSubCategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@AllArgsConstructor
@Slf4j
public class WarehouseSubCategoryServiceImpl implements WarehouseSubCategoryService {

    private final WarehouseSubCategoryRepository warehouseSubCategoryRepository;
    private final WarehouseSubCategoryMapper warehouseSubCategoryMapper;

    @Override
    public ResponseEntity<?> addWarehouseSubCategory(WarehouseSubCategoryForm warehouseSubCategoryForm) {
        try {
            warehouseSubCategoryRepository.save(
                    warehouseSubCategoryMapper.warehouseSubCategoryFormToWarehouseSubCategory(warehouseSubCategoryForm)
            );
            log.info("Warehouse sub category added successfully");
            return buildResponse(null, "Warehouse sub category added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Error while adding warehouse sub category: {} {}", exception.getMessage(), warehouseSubCategoryForm, exception);
            return buildResponse(null, "Warehouse sub category addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateWarehouseSubCategory(WarehouseSubCategoryForm warehouseSubCategoryForm, int id) {
        try {
            warehouseSubCategoryRepository.updateWarehouseSubCategory(warehouseSubCategoryForm.getName(), id);
            log.info("Warehouse sub category updated successfully");
            return buildResponse(null, "Warehouse sub category updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Error while updating warehouse sub category: {} {}", exception.getMessage(), warehouseSubCategoryForm, exception);
            return buildResponse(null, "Warehouse sub category update failed", false, 500);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteWarehouseSubCategory(int id) {
        try {
            warehouseSubCategoryRepository.deleteWarehouseSubCategoryByState(id);
            log.info("Warehouse sub category deleted successfully");
            return buildResponse(null, "Warehouse sub category deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Error while deleting warehouse sub category with ID {}: {}", id, exception.getMessage(), exception);
            return buildResponse(null, "Warehouse sub category deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listWarehouseSubCategories() {
        List<WarehouseSubCategory> wareList = warehouseSubCategoryRepository.getWarehouseSubCategoryListByState();
        log.info("Retrieved list of warehouse sub categories");
        return buildResponse(wareList, "Warehouse sub category list", true, 200);
    }

    @Override
    public ResponseEntity<?> getWarehouseSubCategory(int id) {
        try {
            Optional<WarehouseSubCategory> ware = warehouseSubCategoryRepository.findById(id);
            if (ware.isEmpty()) {
                log.info("Warehouse sub category not found with ID {}", id);
                return buildResponse(null, "Warehouse sub category not found", true, 404);
            }
            log.info("Warehouse sub category found with ID {}", id);
            return buildResponse(ware.get(), "Warehouse sub category found", true, 200);
        } catch (Exception exception) {
            log.error("Error while retrieving warehouse sub category with ID {}: {}", id, exception.getMessage(), exception);
            return buildResponse(null, "Something went wrong", false, 500);
        }
    }
}
