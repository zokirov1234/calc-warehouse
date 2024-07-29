package com.company.service.impl;

import com.company.mapper.MaterialMapper;
import com.company.model.dto.MaterialDto;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.Material;
import com.company.model.form.MaterialForm;
import com.company.repository.AttachRepository;
import com.company.repository.MaterialRepository;
import com.company.service.MaterialService;
import com.company.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final BaseUtil baseUtil;
    private final MaterialMapper materialMapper;
    private final AttachRepository attachRepository;

    @Override
    public ResponseEntity<?> addMaterial(MaterialForm materialForm) {

        ResponseDto<?> responseDto;

        try {
            materialRepository.save(materialMapper.materialFormToMaterial(materialForm));
            log.info("Material added successfully");
            responseDto = baseUtil.convertResponseDto(null, "Material added successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding material {}", exception.getCause(), materialForm);
            responseDto = baseUtil.convertResponseDto(null, "Material added failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> updateMaterial(MaterialForm materialForm, int id) {
        ResponseDto<?> responseDto;

        try {
            materialRepository
                    .updateMaterial(
                            materialForm.getName(),
                            materialForm.getAmount(), materialForm.getAttachId(),
                            materialForm.getArticle(), materialForm.getWidth(),
                            materialForm.getWeight(), materialForm.getBrand(),
                            materialForm.getColor(), materialForm.getCounterpartyId(),
                            materialForm.getWarehouseId(), id
                    );
            log.info("Material updated successfully");
            responseDto = baseUtil.convertResponseDto(null, "Material updated successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating material {}", exception.getCause(), materialForm);
            responseDto = baseUtil.convertResponseDto(null, "Material updated failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> deleteMaterial(int id) {
        ResponseDto<?> responseDto;

        try {
            materialRepository.deleteMaterialByState(id);
            log.info("Material deleted successfully");
            responseDto = baseUtil.convertResponseDto(null, "Material deleted successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting Material {}", exception.getCause(), id);
            responseDto = baseUtil.convertResponseDto(null, "Material deleted failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> listMaterial() {
        ResponseDto<?> responseDto;

        List<MaterialDto> materialDtoList = new ArrayList<>();
        for (Material material : materialRepository.getMaterialListByState()) {
            MaterialDto materialDto = materialMapper.materialToMaterialDto(material);
            if (attachRepository.findById(material.getAttachId()).isEmpty()) {
                materialDto.setImageUrl("");
            } else {
                materialDto.setImageUrl(BaseUtil.baseURL + attachRepository.findById(material.getAttachId()).get().getFileName());
            }
            materialDtoList.add(materialDto);
        }

        log.info("Get list of material");
        responseDto = baseUtil.convertResponseDto(materialDtoList, "Material list", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Override
    public ResponseEntity<?> getMaterial(int id) {
        ResponseDto<?> responseDto;

        try {
            Optional<Material> material = materialRepository.findById(id);
            if (material.isEmpty()) {
                log.info("Material not found");
                responseDto = baseUtil.convertResponseDto(null, "Material not found", true, 404);
                return ResponseEntity.status(404).body(responseDto);
            }
            log.info("Material found");
            MaterialDto materialDto = materialMapper.materialToMaterialDto(material.get());
            if (attachRepository.findById(material.get().getAttachId()).isEmpty()) {
                materialDto.setImageUrl("");
            } else {
                materialDto.setImageUrl(BaseUtil.baseURL + attachRepository.findById(material.get().getAttachId()).get().getFileName());
            }
            responseDto = baseUtil.convertResponseDto(materialDto, "Material found", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong");
            responseDto = baseUtil.convertResponseDto(null, "Something went wrong", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }


}
