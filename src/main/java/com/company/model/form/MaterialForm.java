package com.company.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MaterialForm {

    private String name;
    private int amount;
    private int attachId;
    private String article;
    private double width;
    private double weight;
    private String brand;
    private String color;
    private int counterpartyId;
    private int warehouseId;
}
