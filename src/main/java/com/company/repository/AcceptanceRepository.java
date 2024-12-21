package com.company.repository;

import com.company.model.dto.AcceptanceDto;
import com.company.model.entity.Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcceptanceRepository extends JpaRepository<Acceptance, Integer> {

    @Query("select new com.company.model.dto.AcceptanceDto(a.id, a.acceptanceType, a.warehouseId, a.counterpartyId, a.acceptanceDate, a.currencyId, a.acceptanceStatus, a.description) from Acceptance a")
    List<AcceptanceDto> getAcceptanceDtoList();
}
