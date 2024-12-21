package com.company.mapper;


import com.company.model.entity.MaterialTypes;
import com.company.model.form.MaterialTypesForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialTypesMapper {

    MaterialTypes materialTypesFormToMaterialTypes(MaterialTypesForm materialTypesForm);

}
