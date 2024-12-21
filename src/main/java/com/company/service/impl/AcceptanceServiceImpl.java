package com.company.service.impl;

import com.company.enums.AcceptanceStatus;
import com.company.enums.AcceptanceType;
import com.company.mapper.AcceptanceMapper;
import com.company.model.dto.AcceptanceDto;
import com.company.model.form.AcceptanceCreateForm;
import com.company.repository.AcceptanceRepository;
import com.company.service.AcceptanceService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@Slf4j
@AllArgsConstructor
public class AcceptanceServiceImpl implements AcceptanceService {

    private final AcceptanceRepository acceptanceRepository;
    private final AcceptanceMapper acceptanceMapper;

    @Override
    public ResponseEntity<?> createAcceptance(AcceptanceCreateForm acceptanceCreateForm) {
        try {
            acceptanceRepository.save(acceptanceMapper.acceptanceCreateFormToAcceptance(acceptanceCreateForm));
            log.info("Acceptance added successfully");
            return buildResponse(null, "Acceptance added successfully", true, 200);
        } catch (Exception e) {
            log.error("Error while adding acceptance: {}", e.getMessage());
            return buildResponse(null, "Acceptance addition failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateAcceptance(AcceptanceCreateForm acceptanceCreateForm, int id) {
        if (!updateAcceptanceByForm(acceptanceCreateForm, id)) {
            return buildResponse(null, "Acceptance update failed", false, 404);
        }
        log.info("Acceptance updated successfully");
        return buildResponse(null, "Acceptance updated successfully", true, 200);
    }

    @Override
    public ResponseEntity<?> getAcceptanceById(int acceptanceId) {
        return acceptanceRepository.findById(acceptanceId)
                .map(acceptance -> {
                    log.info("Acceptance id {} found", acceptanceId);
                    return buildResponse(acceptance, "Acceptance found", true, 200);
                })
                .orElseGet(() -> {
                    log.warn("Acceptance id {} not found", acceptanceId);
                    return buildResponse(null, "Acceptance not found", false, 404);
                });
    }

    @Override
    public ResponseEntity<?> deleteAcceptance(int acceptanceId) {
        try {
            acceptanceRepository.deleteById(acceptanceId);
            log.info("Acceptance deleted successfully");
            return buildResponse(null, "Acceptance deleted successfully", true, 200);
        } catch (Exception e) {
            log.error("Error while deleting acceptance: {}", e.getMessage());
            return buildResponse(null, "Acceptance deletion failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listAcceptance() {
        List<AcceptanceDto> allAcceptance = acceptanceRepository.getAcceptanceDtoList();
        return buildResponse(allAcceptance, "Acceptance list retrieved successfully", true, 200);
    }

    @Transactional
    protected boolean updateAcceptanceByForm(AcceptanceCreateForm acceptanceCreateForm, int acceptanceId) {
        return acceptanceRepository.findById(acceptanceId).map(acceptance -> {
            try {
                acceptance.setAcceptanceType(AcceptanceType.valueOf(acceptanceCreateForm.getAcceptanceType()));
                acceptance.setAcceptanceStatus(AcceptanceStatus.valueOf(acceptanceCreateForm.getAcceptanceStatus()));
                acceptance.setWarehouseId(acceptanceCreateForm.getWarehouseId());
                acceptance.setCounterpartyId(acceptanceCreateForm.getCounterpartyId());
                if (acceptanceCreateForm.getAcceptanceDate() != null) {
                    acceptance.setAcceptanceDate(new Timestamp(acceptanceCreateForm.getAcceptanceDate().getTime()));
                }
                acceptance.setCurrencyId(acceptanceCreateForm.getCurrencyId());
                acceptance.setDescription(acceptanceCreateForm.getDescription());
                acceptanceRepository.save(acceptance);
                return true;
            } catch (Exception e) {
                log.error("Error updating acceptance: {}", e.getMessage());
                return false;
            }
        }).orElseGet(() -> {
            log.warn("Acceptance id {} not found", acceptanceId);
            return false;
        });
    }
}
