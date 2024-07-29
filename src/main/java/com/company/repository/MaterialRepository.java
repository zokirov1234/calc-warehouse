package com.company.repository;


import com.company.model.entity.Material;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

    @Modifying
    @Transactional
    @Query("update Material material set material.name = ?1,material.amount = ?2,material.attachId = ?3,material.article = ?4,material.width = ?5,material.weight = ?6,material.brand = ?7,material.color = ?8,material.counterpartyId = ?9,material.warehouseId = ?10 where material.id = ?11")
    void updateMaterial(String name, int amount, int attachId, String article, double width, double weight, String brand, String color, int counterpartyId, int warehouseId ,int id);


    @Modifying
    @Transactional
    @Query("update Material set state = false where id = ?1")
    void deleteMaterialByState(int id);

    @Query("select material from Material material where material.state = true")
    List<Material> getMaterialListByState();

}
