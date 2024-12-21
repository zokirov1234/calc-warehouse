package com.company.service.impl;

import com.company.model.dto.ResponseDto;
import com.company.model.entity.UserWarehouse;
import com.company.model.form.UserWarehouseForm;
import com.company.repository.UserWarehouseRepository;
import com.company.service.UserWarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * addWarehouse method
 * gets entities from db with warehouse id
 * then deletes removed entities
 * then adds newly added entities
 * */
@Slf4j
@Service
@AllArgsConstructor
public class UserWarehouseServiceImpl implements UserWarehouseService {

    private final UserWarehouseRepository userWarehouseRepository;

    @Transactional
    @Override
    public ResponseDto<?> addUserWarehouse(UserWarehouseForm userWarehouseForm) {

        if (userWarehouseForm == null || userWarehouseForm.getWarehouseId() == 0
                || userWarehouseForm.getUserId() == null) {
            return new ResponseDto<>(false, 400, "Invalid user warehouse form", null);
        }
        List<Integer> usersByWarehouseId =
                userWarehouseRepository.getUsersByWarehouseId(userWarehouseForm.getWarehouseId());
        if (usersByWarehouseId.isEmpty()) {
            saveUserWarehouses(userWarehouseForm);
            return new ResponseDto<>(true, 200, "Success", null);
        }
        List<Integer> deleteList = new ArrayList<>(usersByWarehouseId);
        deleteList.removeAll(userWarehouseForm.getUserId());
        log.debug("Deleting with id of users : {}", deleteList);
        try {
            userWarehouseRepository.deleteAllById(deleteList);
        } catch (Exception e) {
            log.error("Deleting user warehouse failed", e);
            return new ResponseDto<>(false, 500, "Delete user warehouse failed", null);
        }
        userWarehouseForm.getUserId().removeAll(usersByWarehouseId);
        if (!userWarehouseForm.getUserId().isEmpty()) {
            saveUserWarehouses(userWarehouseForm);
        }
        return new ResponseDto<>(true, 200, "Success", null);
    }

    @Transactional
    protected void saveUserWarehouses(UserWarehouseForm userWarehouseForm) {
        try {
            userWarehouseRepository.saveAllAndFlush(userWarehouseForm.getUserId()
                    .stream()
                    .map(userId ->
                            UserWarehouse.builder()
                                    .warehouseId(userWarehouseForm.getWarehouseId())
                                    .userId(userId).build())
                    .toList());
        } catch (Exception e) {
            log.error("Error occurred while saving user warehouses", e);
            throw e;
        }
    }
}
