package com.company.service.impl;

import com.company.mapper.WarehouseCategoryMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.WarehouseCategory;
import com.company.model.form.WarehouseCategoryForm;
import com.company.model.form.WarehouseCategoryUserAccessForm;
import com.company.repository.WarehouseCategoryRepository;
import com.company.service.WarehouseCategoryService;
import com.company.service.WarehouseCategoryUserAccessService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@AllArgsConstructor
@Slf4j
public class WarehouseCategoryServiceImpl implements WarehouseCategoryService {

    private final WarehouseCategoryRepository warehouseCategoryRepository;
    private final WarehouseCategoryMapper warehouseCategoryMapper;
    private final WarehouseCategoryUserAccessService warehouseCategoryUserAccessService;

    @Override
    public ResponseEntity<ResponseDto<?>> addWarehouseCategory(WarehouseCategoryForm warehouseCategoryForm) {
        try {
            warehouseCategoryRepository.save(warehouseCategoryMapper.warehouseCategoryFormToWarehouseCategory(warehouseCategoryForm));
            log.info("Warehouse category added successfully");
            return buildResponse(null, "Warehouse category added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while adding warehouse category {}", warehouseCategoryForm, exception);
            return buildResponse(null, "Error adding warehouse category", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseDto<?>> updateWarehouseCategory(WarehouseCategoryForm warehouseCategoryForm, int id) {
        try {
            warehouseCategoryRepository.updateWarehouseCategory(warehouseCategoryForm.getName(), id);
            log.info("Warehouse category updated successfully");
            return buildResponse(null, "Warehouse category updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while updating warehouse category {}", warehouseCategoryForm, exception);
            return buildResponse(null, "Error updating warehouse category", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<ResponseDto<?>> deleteWarehouseCategory(int id) {
        try {
            warehouseCategoryRepository.deleteWarehouseCategoryByState(id);
            log.info("Warehouse category deleted successfully");
            return buildResponse(null, "Warehouse category deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while deleting warehouse category {}", id, exception);
            return buildResponse(null, "Error deleting warehouse category", false, 500);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> listWarehouseCategories() {
        List<WarehouseCategory> wareList = warehouseCategoryRepository.getWarehouseCategoryListByState();
        log.info("Get list of warehouse categories");
        return buildResponse(wareList, "Warehouse category list", true, 200);
    }

    @Override
    public ResponseEntity<ResponseDto<?>> getWarehouseCategory(int id) {
        try {
            Optional<WarehouseCategory> ware = warehouseCategoryRepository.findById(id);
            if (ware.isEmpty()) {
                log.info("Warehouse category not found");
                return buildResponse(null, "Warehouse category not found", true, 404);
            }
            log.info("Warehouse category found");
            return buildResponse(ware.get(), "Warehouse category found", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while retrieving warehouse category {}", id, exception);
            return buildResponse(null, "Error retrieving warehouse category", false, 500);
        }
    }

    @Override
    public ResponseEntity<ResponseDto<?>> addWarehouseCategoryUserAccess(WarehouseCategoryUserAccessForm warehouseCategoryUserAccessForm) {
        ResponseDto<?> responseDto = warehouseCategoryUserAccessService.addWarehouseCategoryUserAccess(warehouseCategoryUserAccessForm);

        if (responseDto == null || !responseDto.getSuccess()) {
            return buildResponse(null,
                    responseDto != null ? responseDto.getMessage() : "Unknown error occurred", false,
                    responseDto != null ? responseDto.getCode() : HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return ResponseEntity.status(responseDto.getCode()).body(responseDto);
    }
}
