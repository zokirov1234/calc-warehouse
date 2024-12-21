package com.company.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyNBUDTO {

    @JsonProperty("title")
    private String title;

    @JsonProperty("code")
    private String code;

    @JsonProperty("cb_price")
    private String cbPrice;

    @JsonProperty("nbu_buy_price")
    private String nbuBuyPrice;

    @JsonProperty("nbu_cell_price")
    private String nbuCellPrice;

    @JsonProperty("date")
    private String date;
}
