package com.company.service;

import com.company.model.dto.ResponseDto;
import com.company.model.form.WarehouseCategoryUserAccessForm;

public interface WarehouseCategoryUserAccessService {

    ResponseDto<?> addWarehouseCategoryUserAccess(WarehouseCategoryUserAccessForm warehouseCategoryUserAccessForm);
}
