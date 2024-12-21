package com.company.mapper;


import com.company.model.entity.AcceptanceInfo;
import com.company.model.form.AcceptanceInfoCreateForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AcceptanceInfoMapper {

    AcceptanceInfo acceptanceInfoCreateFormToAcceptanceInfo(AcceptanceInfoCreateForm acceptanceInfoCreateForm);

}
