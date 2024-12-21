package com.company.repository;

import com.company.model.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

    @Modifying
    @Query("update Workshop set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select w from Workshop w where w.state = true order by w.name desc")
    List<Workshop> getList();

}
