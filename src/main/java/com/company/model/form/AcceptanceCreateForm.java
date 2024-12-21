package com.company.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AcceptanceCreateForm {

    private String acceptanceType;
    private int warehouseId;
    private int counterpartyId;
    private Date acceptanceDate;
    private int currencyId;
    private String acceptanceStatus;
    private String description;
}
