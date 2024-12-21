package com.company.service.impl;

import com.company.mapper.WarehouseMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.Warehouse;
import com.company.model.form.UserWarehouseForm;
import com.company.model.form.WarehouseForm;
import com.company.repository.WarehouseRepository;
import com.company.service.UserWarehouseService;
import com.company.service.WarehouseService;
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
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;
    private final UserWarehouseService userWarehouseService;

    @Override
    public ResponseEntity<?> addWarehouse(WarehouseForm warehouseForm) {
        try {
            warehouseRepository.save(warehouseMapper.warehouseFormToWarehouse(warehouseForm));
            log.info("Warehouse added successfully");
            return buildResponse(null, "Warehouse added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while adding warehouse: {} {}", exception.getMessage(), warehouseForm, exception);
            return buildResponse(null, "Warehouse addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateWarehouse(WarehouseForm warehouseForm, int id) {
        try {
            warehouseRepository.updateWarehouse(warehouseForm.getName(), id);
            log.info("Warehouse updated successfully");
            return buildResponse(null, "Warehouse updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while updating warehouse: {} {}", exception.getMessage(), warehouseForm, exception);
            return buildResponse(null, "Warehouse update failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteWarehouse(int id) {
        try {
            warehouseRepository.deleteWarehouseByState(id);
            log.info("Warehouse deleted successfully");
            return buildResponse(null, "Warehouse deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while deleting warehouse: {}", id, exception);
            return buildResponse(null, "Warehouse deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listWarehouses() {
        List<Warehouse> wareList = warehouseRepository.getWarehouseListByState();
        log.info("Get list of warehouses");
        return buildResponse(wareList, "Warehouse list", true, 200);
    }

    @Override
    public ResponseEntity<?> getWarehouse(int id) {
        try {
            Optional<Warehouse> ware = warehouseRepository.findById(id);
            if (ware.isEmpty()) {
                log.info("Warehouse not found");
                return buildResponse(null, "Warehouse not found", true, 404);
            }
            log.info("Warehouse found");
            return buildResponse(ware.get(), "Warehouse found", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while retrieving warehouse: {}", id, exception);
            return buildResponse(null, "Something went wrong", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> addUserWarehouse(UserWarehouseForm userWarehouseForm) {
        ResponseDto<?> responseDto = userWarehouseService.addUserWarehouse(userWarehouseForm);

        if (responseDto == null || !responseDto.getSuccess()) {
            return buildResponse(
                    null,
                    responseDto != null ? responseDto.getMessage() : "Unknown error occurred",
                    false,
                    responseDto != null ? responseDto.getCode() : 500
            );
        }

        return ResponseEntity.status(responseDto.getCode()).body(responseDto);
    }
}
