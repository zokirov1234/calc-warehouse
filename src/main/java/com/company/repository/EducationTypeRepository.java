package com.company.repository;

import com.company.model.entity.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationTypeRepository extends JpaRepository<EducationType, Integer> {

    @Modifying
    @Query("update EducationType set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select education from EducationType education where education.state = true order by education.name desc")
    List<EducationType> getList();
}
