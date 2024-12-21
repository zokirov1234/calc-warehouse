package com.company.repository;

import com.company.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

    @Modifying
    @Query("update Roles set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select role from Roles role where role.state = true order by role.name desc")
    List<Roles> getList();

}
