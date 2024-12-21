package com.company.service.impl;

import com.company.mapper.EducationTypeMapper;
import com.company.model.entity.EducationType;
import com.company.model.form.EducationTypeForm;
import com.company.repository.EducationTypeRepository;
import com.company.service.EducationTypeService;
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
public class EducationTypeServiceImpl implements EducationTypeService {

    private final EducationTypeRepository educationTypeRepository;
    private final EducationTypeMapper educationTypeMapper;

    @Override
    public ResponseEntity<?> add(EducationTypeForm educationTypeForm) {
        try {
            educationTypeRepository.save(educationTypeMapper.educationTypeFormToEducation(educationTypeForm));
            log.info("Education type added successfully");
            return buildResponse(null, "Education type added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding education type {}", exception.getCause(), educationTypeForm);
            return buildResponse(null, "Education type addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, EducationTypeForm educationTypeForm) {
        try {
            educationTypeRepository.updateByName(educationTypeForm.getName(), id);
            log.info("Education type updated successfully");
            return buildResponse(null, "Education type updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating education type {}", exception.getCause(), educationTypeForm);
            return buildResponse(null, "Education type update failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int educationTypeId) {
        try {
            educationTypeRepository.deleteById(educationTypeId);
            log.info("Education type deleted successfully");
            return buildResponse(null, "Education type deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting education type {}", exception.getCause(), educationTypeId);
            return buildResponse(null, "Education type deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int educationTypeId) {
        Optional<EducationType> educationType = educationTypeRepository.findById(educationTypeId);
        if (educationType.isEmpty()) {
            log.info("Education type id {} not found", educationTypeId);
            return buildResponse(null, "Education type not found", false, 404);
        }
        return buildResponse(educationType.get(), "Education type found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<EducationType> allEducationTypes = educationTypeRepository.getList();
        return buildResponse(allEducationTypes, "Education types found", true, 200);
    }
}
