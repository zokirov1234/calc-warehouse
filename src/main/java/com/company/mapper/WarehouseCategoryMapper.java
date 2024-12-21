package com.company.mapper;


import com.company.model.entity.WarehouseCategory;
import com.company.model.form.WarehouseCategoryForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseCategoryMapper {

    WarehouseCategory warehouseCategoryFormToWarehouseCategory(WarehouseCategoryForm warehouseCategoryForm);

}
