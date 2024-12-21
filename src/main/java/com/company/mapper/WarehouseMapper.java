package com.company.mapper;


import com.company.model.entity.Warehouse;
import com.company.model.form.WarehouseForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    Warehouse warehouseFormToWarehouse(WarehouseForm warehouseForm);

}
