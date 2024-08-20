package com.company.mapper;


import com.company.model.entity.EducationType;
import com.company.model.form.EducationTypeForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EducationTypeMapper {

    EducationType educationTypeFormToEducation(EducationTypeForm educationTypeForm);

}
