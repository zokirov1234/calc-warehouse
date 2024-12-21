package com.company.model.dto;

import com.company.enums.AcceptanceStatus;
import com.company.enums.AcceptanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcceptanceDto {

    private int id;
    private AcceptanceType acceptanceType;
    private int warehouseId;
    private int counterpartyId;
    private Timestamp acceptanceDate;
    private int currencyId;
    private AcceptanceStatus acceptanceStatus;
    private String description;
}
