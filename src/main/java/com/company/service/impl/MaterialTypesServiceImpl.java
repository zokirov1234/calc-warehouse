package com.company.service.impl;

import com.company.mapper.MaterialTypesMapper;
import com.company.model.entity.MaterialTypes;
import com.company.model.form.MaterialTypesForm;
import com.company.repository.MaterialTypesRepository;
import com.company.service.MaterialTypesService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@Slf4j
@AllArgsConstructor
public class MaterialTypesServiceImpl implements MaterialTypesService {

    private final MaterialTypesRepository materialTypesRepository;
    private final MaterialTypesMapper materialTypesMapper;

    @Override
    public ResponseEntity<?> add(MaterialTypesForm materialTypesForm) {
        try {
            materialTypesRepository.save(materialTypesMapper.materialTypesFormToMaterialTypes(materialTypesForm));
            log.info("MaterialTypes added successfully");
            return buildResponse(null, "MaterialTypes added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding materialTypes {}", exception.getCause(), materialTypesForm);
            return buildResponse(null, "MaterialTypes addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, MaterialTypesForm materialTypesForm) {
        try {
            materialTypesRepository.updateMaterialTypes(materialTypesForm.getName(), materialTypesForm.getShortName(), id);
            log.info("MaterialTypes updated successfully");
            return buildResponse(null, "MaterialTypes updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating materialTypes {}", exception.getCause(), materialTypesForm);
            return buildResponse(null, "MaterialTypes update failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int materialTypesId) {
        try {
            materialTypesRepository.deleteById(materialTypesId);
            log.info("MaterialTypes deleted successfully");
            return buildResponse(null, "MaterialTypes deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting materialTypes {}", exception.getCause(), materialTypesId);
            return buildResponse(null, "MaterialTypes deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int materialTypesId) {
        Optional<MaterialTypes> materialTypes = materialTypesRepository.findById(materialTypesId);

        if (materialTypes.isEmpty()) {
            log.info("MaterialTypes id {} not found", materialTypesId);
            return buildResponse(null, "MaterialTypes not found", false, 404);
        }

        log.info("MaterialTypes id {} found", materialTypesId);
        return buildResponse(materialTypes.get(), "MaterialTypes found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<MaterialTypes> allMaterialTypes = materialTypesRepository.getList();
        return buildResponse(allMaterialTypes, "MaterialTypes list found", true, 200);
    }
}
