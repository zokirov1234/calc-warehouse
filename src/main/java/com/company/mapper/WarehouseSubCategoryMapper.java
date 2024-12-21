package com.company.mapper;


import com.company.model.entity.WarehouseSubCategory;
import com.company.model.form.WarehouseSubCategoryForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseSubCategoryMapper {

    WarehouseSubCategory warehouseSubCategoryFormToWarehouseSubCategory(WarehouseSubCategoryForm warehouseSubCategoryForm);

}
