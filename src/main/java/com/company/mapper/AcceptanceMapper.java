package com.company.mapper;


import com.company.model.entity.Acceptance;
import com.company.model.form.AcceptanceCreateForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcceptanceMapper {

    Acceptance acceptanceCreateFormToAcceptance(AcceptanceCreateForm acceptanceCreateForm);

}
