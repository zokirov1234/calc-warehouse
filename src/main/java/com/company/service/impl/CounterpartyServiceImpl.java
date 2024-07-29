package com.company.service.impl;

import com.company.enums.TypeCounterparty;
import com.company.mapper.CounterpartyMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.Counterparty;
import com.company.model.form.CounterpartyForm;
import com.company.repository.CounterpartyRepository;
import com.company.service.CounterpartyService;
import com.company.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;
    private final BaseUtil baseUtil;
    private final CounterpartyMapper counterpartyMapper;

    @Override
    public ResponseEntity<?> addCounterparty(CounterpartyForm counterpartyForm) {

        ResponseDto<?> responseDto;

        try {
            counterpartyRepository.save(counterpartyMapper.counterpartyFormToCounterparty(counterpartyForm));
            log.info("Counterparty added successfully");
            responseDto = baseUtil.convertResponseDto(null, "Counterparty added successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding counterparty {}", exception.getCause(), counterpartyForm);
            responseDto = baseUtil.convertResponseDto(null, "Counterparty added failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> updateCounterparty(CounterpartyForm counterpartyForm, int id) {
        ResponseDto<?> responseDto;

        try {
            counterpartyRepository
                    .updateCounterparty(
                            counterpartyForm.getName(),
                            counterpartyForm.getLastName(), TypeCounterparty.valueOf(counterpartyForm.getType()),
                            counterpartyForm.getPhone(), counterpartyForm.getAddress(),
                            counterpartyForm.getDescription(), id
                    );
            log.info("Counterparty updated successfully");
            responseDto = baseUtil.convertResponseDto(null, "Counterparty updated successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating counterparty {}", exception.getCause(), counterpartyForm);
            responseDto = baseUtil.convertResponseDto(null, "Counterparty updated failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> deleteCounterparty(int id) {
        ResponseDto<?> responseDto;

        try {
            counterpartyRepository.deleteCounterpartyByState(id);
            log.info("Counterparty deleted successfully");
            responseDto = baseUtil.convertResponseDto(null, "Counterparty deleted successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting Counterparty {}", exception.getCause(), id);
            responseDto = baseUtil.convertResponseDto(null, "Counterparty deleted failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> listCounterparties() {
        ResponseDto<?> responseDto;

        List<Counterparty> counterpartyList = counterpartyRepository.getCounterpartyListByState();

        log.info("Get list of counterparties");
        responseDto = baseUtil.convertResponseDto(counterpartyList, "Counterparty list", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Override
    public ResponseEntity<?> getCounterparty(int id) {
        ResponseDto<?> responseDto;

        try {
            Optional<Counterparty> counterparty = counterpartyRepository.findById(id);
            if (counterparty.isEmpty()) {
                log.info("Counterparty not found");
                responseDto = baseUtil.convertResponseDto(null, "Counterparty not found", true, 404);
                return ResponseEntity.status(404).body(responseDto);
            }
            log.info("Counterparty found");
            responseDto = baseUtil.convertResponseDto(counterparty.get(), "Counterparty found", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong");
            responseDto = baseUtil.convertResponseDto(null, "Something went wrong", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }


}
