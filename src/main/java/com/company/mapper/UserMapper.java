package com.company.mapper;


import com.company.model.dto.UserDto;
import com.company.model.entity.UserEntity;
import com.company.model.form.UserForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "roles", target = "role")
    @Mapping(source = "createdAt", target = "registeredAt")
    UserDto UserToUserDTO(UserEntity user);


    @Mapping(source = "role", target = "roles")
    UserEntity UserFormToUserEntity(UserForm userForm);

}
