package com.company.service.impl;

import com.company.model.dto.ResponseDto;
import com.company.model.entity.WarehouseCategoryUserAccess;
import com.company.model.form.WarehouseCategoryUserAccessForm;
import com.company.repository.WarehouseCategoryUserAccessRepository;
import com.company.service.WarehouseCategoryUserAccessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class WarehouseCategoryUserAccessServiceImpl implements WarehouseCategoryUserAccessService {

    private final WarehouseCategoryUserAccessRepository warehouseCategoryUserAccessRepository;

    @Transactional
    @Override
    public ResponseDto<?> addWarehouseCategoryUserAccess(WarehouseCategoryUserAccessForm warehouseCategoryUserAccessForm) {

        if (warehouseCategoryUserAccessForm == null || warehouseCategoryUserAccessForm.getWarehouseCategoryId() == 0
                || warehouseCategoryUserAccessForm.getUserId() == null) {
            return new ResponseDto<>(false, 400, "Invalid warehouse category user access form", null);
        }
        List<Integer> usersByWarehouseCategoryId =
                warehouseCategoryUserAccessRepository.getUsersByWarehouseCategoryId(warehouseCategoryUserAccessForm.getWarehouseCategoryId());
        if (usersByWarehouseCategoryId.isEmpty()) {
            saveUserWarehouseCategory(warehouseCategoryUserAccessForm);
            return new ResponseDto<>(true, 200, "Success", null);
        }
        List<Integer> deleteList = new ArrayList<>(usersByWarehouseCategoryId);
        deleteList.removeAll(warehouseCategoryUserAccessForm.getUserId());
        log.debug("Deleting with id of users : {}", deleteList);
        try {
            warehouseCategoryUserAccessRepository.deleteAllById(deleteList);
        } catch (Exception e) {
            log.error("Deleting warehouse category user access failed", e);
            return new ResponseDto<>(false, 500, "Delete warehouse category user access  failed", null);
        }
        warehouseCategoryUserAccessForm.getUserId().removeAll(usersByWarehouseCategoryId);
        if (!warehouseCategoryUserAccessForm.getUserId().isEmpty()) {
            saveUserWarehouseCategory(warehouseCategoryUserAccessForm);
        }
        return new ResponseDto<>(true, 200, "Success", null);
    }

    @Transactional
    protected void saveUserWarehouseCategory(WarehouseCategoryUserAccessForm warehouseCategoryUserAccessForm) {
        try {
            warehouseCategoryUserAccessRepository.saveAllAndFlush(warehouseCategoryUserAccessForm.getUserId()
                    .stream()
                    .map(userId ->
                            WarehouseCategoryUserAccess.builder()
                                    .warehouseCategoryId(warehouseCategoryUserAccessForm.getWarehouseCategoryId())
                                    .userId(userId).build())
                    .toList());
        } catch (Exception e) {
            log.error("Error occurred while saving user to warehouse category", e);
            throw e;
        }
    }
}
