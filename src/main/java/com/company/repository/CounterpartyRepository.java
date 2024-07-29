package com.company.repository;

import com.company.enums.TypeCounterparty;
import com.company.model.entity.Counterparty;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {

    @Modifying
    @Transactional
    @Query("update Counterparty counterparty set counterparty.name = ?1, counterparty.lastName = ?2, counterparty.typeCounterparty = ?3, counterparty.phone = ?4, counterparty.address = ?5, counterparty.description = ?6 where counterparty.id = ?7")
    void updateCounterparty(String name, String lastName, TypeCounterparty type, String phone, String address, String description, int id);


    @Modifying
    @Transactional
    @Query("update Counterparty set state = false where id = ?1")
    void deleteCounterpartyByState(int id);

    @Query("select counterparty from Counterparty counterparty where counterparty.state = true")
    List<Counterparty> getCounterpartyListByState();

}
