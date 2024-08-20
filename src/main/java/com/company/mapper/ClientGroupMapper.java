package com.company.mapper;


import com.company.model.entity.ClientGroup;
import com.company.model.form.ClientGroupForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientGroupMapper {

    ClientGroup clientGroupFormToClientGroup(ClientGroupForm clientGroupForm);

}
