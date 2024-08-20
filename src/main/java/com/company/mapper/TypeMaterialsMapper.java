package com.company.mapper;


import com.company.model.entity.TypeMaterials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMaterialsMapper {

    TypeMaterials typeMaterialsFormToTypeMaterials(TypeMaterials typeMaterials);

}
