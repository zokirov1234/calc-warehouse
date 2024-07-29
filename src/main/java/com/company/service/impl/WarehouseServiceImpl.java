package com.company.service.impl;

import com.company.mapper.WarehouseMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.Warehouse;
import com.company.model.form.WarehouseForm;
import com.company.repository.WarehouseRepository;
import com.company.service.WarehouseService;
import com.company.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final BaseUtil baseUtil;
    private final WarehouseMapper warehouseMapper;

    @Override
    public ResponseEntity<?> addWarehouse(WarehouseForm warehouseForm) {

        ResponseDto<?> responseDto;

        try {
            warehouseRepository.save(warehouseMapper.warehouseFormToWarehouse(warehouseForm));
            log.info("Warehouse added successfully");
            responseDto = baseUtil.convertResponseDto(null, "Warehouse added successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding warehouse {}", exception.getCause(), warehouseForm);
            responseDto = baseUtil.convertResponseDto(null, "Warehouse added failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> updateWarehouse(WarehouseForm warehouseForm, int id) {
        ResponseDto<?> responseDto;

        try {
            warehouseRepository.updateWarehouse(warehouseForm.getName(), id);
            log.info("Warehouse updated successfully");
            responseDto = baseUtil.convertResponseDto(null, "Warehouse updated successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating warehouse {}", exception.getCause(), warehouseForm);
            responseDto = baseUtil.convertResponseDto(null, "Warehouse updated failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> deleteWarehouse(int id) {
        ResponseDto<?> responseDto;

        try {
            warehouseRepository.deleteWarehouseByState(id);
            log.info("Warehouse deleted successfully");
            responseDto = baseUtil.convertResponseDto(null, "Warehouse deleted successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting warehouse {}", exception.getCause(), id);
            responseDto = baseUtil.convertResponseDto(null, "Warehouse deleted failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> listWarehouses() {
        ResponseDto<?> responseDto;

        List<Warehouse> wareList = warehouseRepository.getWarehouseListByState();

        log.info("Get list of warehouses");
        responseDto = baseUtil.convertResponseDto(wareList, "Warehouse list", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Override
    public ResponseEntity<?> getWarehouse(int id) {
        ResponseDto<?> responseDto;

        try {
            Optional<Warehouse> ware = warehouseRepository.findById(id);
            if (ware.isEmpty()) {
                log.info("Warehouse not found");
                responseDto = baseUtil.convertResponseDto(null, "Warehouse not found", true, 404);
                return ResponseEntity.status(404).body(responseDto);
            }
            log.info("Warehouse found");
            responseDto = baseUtil.convertResponseDto(ware.get(), "Warehouse found", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong");
            responseDto = baseUtil.convertResponseDto(null, "Something went wrong", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }


}
