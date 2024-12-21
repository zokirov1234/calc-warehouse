package com.company.repository;

import com.company.model.entity.MaterialTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialTypesRepository extends JpaRepository<MaterialTypes, Integer> {

    @Modifying
    @Query("update MaterialTypes set name = ?1, shortName = ?2 where id = ?3")
    void updateMaterialTypes(String name, String shortName, int id);

    @Query("select material from MaterialTypes material where material.state = true order by material.name desc")
    List<MaterialTypes> getList();
}
