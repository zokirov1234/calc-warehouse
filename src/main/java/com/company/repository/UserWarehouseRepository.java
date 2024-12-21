package com.company.repository;

import com.company.model.entity.UserWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserWarehouseRepository extends JpaRepository<UserWarehouse, Integer> {

    @Transactional(readOnly = true)
    @Query("select user_warehouse.userId from UserWarehouse user_warehouse where user_warehouse.warehouseId = ?1")
    List<Integer> getUsersByWarehouseId(int warehouseId);
}
