package com.company.mapper;


import com.company.model.entity.Currencies;
import com.company.model.form.CurrenciesForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrenciesMapper {

    Currencies currenciesFormToCurrencies(CurrenciesForm currenciesForm);

}
