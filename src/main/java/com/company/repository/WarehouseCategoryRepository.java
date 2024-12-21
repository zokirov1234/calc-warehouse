package com.company.repository;

import com.company.model.entity.WarehouseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseCategoryRepository extends JpaRepository<WarehouseCategory, Integer> {

    @Modifying
    @Query("update WarehouseCategory warehouse_category set warehouse_category.name = ?1 where warehouse_category.id = ?2")
    void updateWarehouseCategory(String name, int id);


    @Modifying
    @Query("update WarehouseCategory set state = false where id = ?1")
    void deleteWarehouseCategoryByState(int id);

    @Query("select ware from WarehouseCategory ware where ware.state = true")
    List<WarehouseCategory> getWarehouseCategoryListByState();
}
