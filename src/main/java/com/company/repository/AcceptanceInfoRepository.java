package com.company.repository;

import com.company.model.dto.AcceptanceInfoDto;
import com.company.model.entity.AcceptanceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcceptanceInfoRepository extends JpaRepository<AcceptanceInfo, Integer> {

    @Query("select new com.company.model.dto.AcceptanceInfoDto(a.id, a.measure, a.pricePerUnit, a.consumptionPerUnit, a.price, a.acceptanceId, a.warehouseCategoryId, a.warehouseSubCategoryId, a.materialId) from AcceptanceInfo a")
    List<AcceptanceInfoDto> getAcceptanceInfoDtoList();
}
