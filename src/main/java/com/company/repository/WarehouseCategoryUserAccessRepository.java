package com.company.repository;

import com.company.model.entity.WarehouseCategoryUserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarehouseCategoryUserAccessRepository extends JpaRepository<WarehouseCategoryUserAccess, Integer> {

    @Transactional(readOnly = true)
    @Query("select wcu.userId from WarehouseCategoryUserAccess wcu where wcu.warehouseCategoryId = ?1")
    List<Integer> getUsersByWarehouseCategoryId(int warehouseCategoryId);
}
