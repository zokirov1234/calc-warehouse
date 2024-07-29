package com.company.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanMapper {


    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public WarehouseMapper warehouseMapper() {
        return Mappers.getMapper(WarehouseMapper.class);
    }

    @Bean
    public CounterpartyMapper counterpartyMapper() {
        return Mappers.getMapper(CounterpartyMapper.class);
    }

    @Bean
    public MaterialMapper materialMapper() {
        return Mappers.getMapper(MaterialMapper.class);
    }
}
