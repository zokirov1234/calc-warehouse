package com.company.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDto {

    private int id;

    private String name;

    private int amount;

    private String imageUrl;

    private String article;

    private double width;

    private double weight;

    private String brand;

    private String color;

    private String warehouseName;

    private String counterpartyName;
}
