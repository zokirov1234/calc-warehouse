package com.company.repository;

import com.company.model.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permissions, Integer> {

    @Modifying
    @Query("update Permissions set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select permission from Permissions permission where permission.state = true order by permission.name desc")
    List<Permissions> getList();

}
