package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcceptanceInfoDto {

    private int id;
    private int measure;
    private double pricePerUnit;
    private double consumptionPerUnit;
    private double price;
    private int acceptanceId;
    private int warehouseCategoryId;
    private int warehouseSubCategoryId;
    private int materialId;
}
