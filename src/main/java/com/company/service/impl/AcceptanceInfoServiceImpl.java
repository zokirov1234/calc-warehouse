package com.company.service.impl;

import com.company.mapper.AcceptanceInfoMapper;
import com.company.model.dto.AcceptanceInfoDto;
import com.company.model.form.AcceptanceInfoCreateForm;
import com.company.repository.AcceptanceInfoRepository;
import com.company.service.AcceptanceInfoService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@Slf4j
@AllArgsConstructor
public class AcceptanceInfoServiceImpl implements AcceptanceInfoService {

    private final AcceptanceInfoRepository acceptanceInfoRepository;
    private final AcceptanceInfoMapper acceptanceInfoMapper;

    @Override
    public ResponseEntity<?> createAcceptanceInfo(AcceptanceInfoCreateForm acceptanceInfoCreateForm) {
        try {
            acceptanceInfoRepository.save(acceptanceInfoMapper.acceptanceInfoCreateFormToAcceptanceInfo(acceptanceInfoCreateForm));
            log.info("AcceptanceInfo added successfully");
            return buildResponse(null, "AcceptanceInfo added successfully", true, 200);
        } catch (Exception e) {
            log.error("Error while adding acceptanceInfo: {}", e.getMessage());
            return buildResponse(null, "AcceptanceInfo addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateAcceptanceInfo(AcceptanceInfoCreateForm acceptanceInfoCreateForm, int id) {
        if (!updateAcceptanceInfoByForm(acceptanceInfoCreateForm, id)) {
            return buildResponse(null, "AcceptanceInfo update failed", false, 404);
        }
        log.info("AcceptanceInfo updated successfully");
        return buildResponse(null, "AcceptanceInfo updated successfully", true, 200);
    }

    @Override
    public ResponseEntity<?> getAcceptanceInfoById(int acceptanceInfoId) {
        return acceptanceInfoRepository.findById(acceptanceInfoId)
                .map(acceptanceInfo -> {
                    log.info("AcceptanceInfo id {} found", acceptanceInfo);
                    return buildResponse(acceptanceInfo, "AcceptanceInfo found", true, 200);
                })
                .orElseGet(() -> {
                    log.warn("AcceptanceInfo id {} not found", acceptanceInfoId);
                    return buildResponse(null, "AcceptanceInfo not found", false, 404);
                });
    }

    @Override
    public ResponseEntity<?> deleteAcceptanceInfo(int acceptanceInfoId) {
        try {
            acceptanceInfoRepository.deleteById(acceptanceInfoId);
            log.info("AcceptanceInfo deleted successfully");
            return buildResponse(null, "AcceptanceInfo deleted successfully", true, 200);
        } catch (Exception e) {
            log.error("Error while deleting acceptance info: {}", e.getMessage());
            return buildResponse(null, "AcceptanceInfo deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listAcceptanceInfo() {
        List<AcceptanceInfoDto> allAcceptanceInfo = acceptanceInfoRepository.getAcceptanceInfoDtoList();
        return buildResponse(allAcceptanceInfo, "AcceptanceInfo list retrieved successfully", true, 200);
    }

    @Transactional
    protected boolean updateAcceptanceInfoByForm(AcceptanceInfoCreateForm acceptanceInfoCreateForm, int acceptanceId) {
        return acceptanceInfoRepository.findById(acceptanceId).map(acceptanceInfo -> {
            try {
                acceptanceInfo.setMeasure(acceptanceInfoCreateForm.getMeasure());
                acceptanceInfo.setPricePerUnit(acceptanceInfoCreateForm.getPricePerUnit());
                acceptanceInfo.setConsumptionPerUnit(acceptanceInfoCreateForm.getConsumptionPerUnit());
                acceptanceInfo.setPrice(acceptanceInfoCreateForm.getPrice());
                acceptanceInfo.setAcceptanceId(acceptanceInfoCreateForm.getAcceptanceId());
                acceptanceInfo.setWarehouseCategoryId(acceptanceInfoCreateForm.getWarehouseCategoryId());
                acceptanceInfo.setMaterialId(acceptanceInfoCreateForm.getMaterialId());
                acceptanceInfoRepository.save(acceptanceInfo);
                return true;
            } catch (Exception e) {
                log.error("Error updating acceptanceInfo: {}", e.getMessage());
                return false;
            }
        }).orElseGet(() -> {
            log.warn("AcceptanceInfo id {} not found", acceptanceId);
            return false;
        });
    }
}
