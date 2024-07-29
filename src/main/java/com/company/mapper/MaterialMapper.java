package com.company.mapper;


import com.company.model.dto.MaterialDto;
import com.company.model.entity.Counterparty;
import com.company.model.entity.Material;
import com.company.model.form.CounterpartyForm;
import com.company.model.form.MaterialForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    Material materialFormToMaterial(MaterialForm materialForm);

    MaterialDto materialToMaterialDto(Material material);

}
