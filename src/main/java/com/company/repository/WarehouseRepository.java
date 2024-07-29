package com.company.repository;


import com.company.model.entity.Warehouse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    @Modifying
    @Transactional
    @Query("update Warehouse warehosue set warehosue.name = ?1 where warehosue.id = ?2")
    void updateWarehouse(String name, int id);


    @Modifying
    @Transactional
    @Query("update Warehouse set state = false where id = ?1")
    void deleteWarehouseByState(int id);

    @Query("select ware from Warehouse ware where ware.state = true")
    List<Warehouse> getWarehouseListByState();

}
