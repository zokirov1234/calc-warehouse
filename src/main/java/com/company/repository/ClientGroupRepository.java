package com.company.repository;

import com.company.model.entity.ClientGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientGroupRepository extends JpaRepository<ClientGroup, Integer> {

    @Transactional
    @Modifying
    @Query("update ClientGroup set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select client from ClientGroup client where client.state = true order by client.name desc")
    List<ClientGroup> getList();

}
