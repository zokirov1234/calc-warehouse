package com.company.service;

import com.company.model.dto.ResponseDto;
import com.company.model.form.UserWarehouseForm;

public interface UserWarehouseService {

    ResponseDto<?> addUserWarehouse(UserWarehouseForm userWarehouseForm);
}
