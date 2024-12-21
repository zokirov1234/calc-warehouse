package com.company.model.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AcceptanceInfoCreateForm {

    private int measure;
    private double pricePerUnit;
    private double consumptionPerUnit;
    private double price;
    private int acceptanceId;
    private int warehouseCategoryId;
    private int warehouseSubCategoryId;
    private int materialId;
}
