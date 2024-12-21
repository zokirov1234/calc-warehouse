package com.company.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WarehouseSubCategoryForm {

    private String name;
    private int typeId;
    private int minCount;
}
