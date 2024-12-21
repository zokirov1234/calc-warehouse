package com.company.service.impl;

import com.company.mapper.MaterialMapper;
import com.company.model.dto.MaterialDto;
import com.company.model.entity.Material;
import com.company.model.form.MaterialForm;
import com.company.repository.AttachRepository;
import com.company.repository.MaterialRepository;
import com.company.service.MaterialService;
import com.company.util.Constants;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final AttachRepository attachRepository;

    @Override
    public ResponseEntity<?> addMaterial(MaterialForm materialForm) {
        try {
            materialRepository.save(materialMapper.materialFormToMaterial(materialForm));
            log.info("Material added successfully");
            return buildResponse(null, "Material added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while adding material {}", exception.getCause(), materialForm);
            return buildResponse(null, "Material addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateMaterial(MaterialForm materialForm, int id) {
        try {
            materialRepository.updateMaterial(
                    materialForm.getName(), materialForm.getAmount(),
                    materialForm.getAttachId(), materialForm.getArticle(),
                    materialForm.getWidth(), materialForm.getWeight(),
                    materialForm.getBrand(), materialForm.getColor(),
                    materialForm.getCounterpartyId(), materialForm.getWarehouseId(),
                    id
            );
            log.info("Material updated successfully");
            return buildResponse(null, "Material updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while updating material {}", exception.getCause(), materialForm);
            return buildResponse(null, "Material update failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteMaterial(int id) {
        try {
            materialRepository.deleteMaterialByState(id);
            log.info("Material deleted successfully");
            return buildResponse(null, "Material deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong: {} while deleting material {}", exception.getCause(), id);
            return buildResponse(null, "Material deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listMaterial() {
        List<MaterialDto> materialDtoList = new ArrayList<>();
        for (Material material : materialRepository.getMaterialListByState()) {
            MaterialDto materialDto = materialMapper.materialToMaterialDto(material);
            materialDto.setImageUrl(
                    attachRepository.findById(material.getAttachId())
                            .map(attach -> Constants.IMAGE_BASE_URL + attach.getFileName())
                            .orElse("")
            );
            materialDtoList.add(materialDto);
        }
        log.info("Material list retrieved");
        return buildResponse(materialDtoList, "Material list retrieved", true, 200);
    }

    @Override
    public ResponseEntity<?> getMaterial(int id) {
        try {
            Optional<Material> material = materialRepository.findById(id);
            if (material.isEmpty()) {
                log.info("Material not found");
                return buildResponse(null, "Material not found", false, 404);
            }
            MaterialDto materialDto = materialMapper.materialToMaterialDto(material.get());
            materialDto.setImageUrl(
                    attachRepository.findById(material.get().getAttachId())
                            .map(attach -> Constants.IMAGE_BASE_URL + attach.getFileName())
                            .orElse("")
            );
            return buildResponse(materialDto, "Material found", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong while retrieving material: {}", exception.getMessage());
            return buildResponse(null, "Something went wrong", false, 500);
        }
    }
}
