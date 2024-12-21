package com.company.repository;

import com.company.model.entity.WarehouseSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseSubCategoryRepository extends JpaRepository<WarehouseSubCategory, Integer> {
    @Modifying
    @Query("update WarehouseSubCategory warehouse_sub_category set warehouse_sub_category.name = ?1 where warehouse_sub_category.id = ?2")
    void updateWarehouseSubCategory(String name, int id);


    @Modifying
    @Query("update WarehouseSubCategory set state = false where id = ?1")
    void deleteWarehouseSubCategoryByState(int id);

    @Query("select ware from WarehouseSubCategory ware where ware.state = true")
    List<WarehouseSubCategory> getWarehouseSubCategoryListByState();
}
