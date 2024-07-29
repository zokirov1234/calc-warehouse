package com.company.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CounterpartyForm {

    private String name;
    private String type;
    private String lastName;
    private String phone;
    private String address;
    private String description;

}
