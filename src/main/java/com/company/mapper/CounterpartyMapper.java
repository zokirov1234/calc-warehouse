package com.company.mapper;


import com.company.model.entity.Counterparty;
import com.company.model.form.CounterpartyForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CounterpartyMapper {

    Counterparty counterpartyFormToCounterparty(CounterpartyForm categoryForm);

}
