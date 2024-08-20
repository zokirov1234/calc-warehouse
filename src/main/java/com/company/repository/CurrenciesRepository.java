package com.company.repository;

import com.company.model.entity.Currencies;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrenciesRepository extends JpaRepository<Currencies, Integer> {

    @Transactional
    @Modifying
    @Query("update Currencies set name = ?1 where id = ?2")
    void updateByName(String name, int id);

    @Query("select currency from Currencies currency where currency.state = true order by currency.name desc")
    List<Currencies> getList();
}
