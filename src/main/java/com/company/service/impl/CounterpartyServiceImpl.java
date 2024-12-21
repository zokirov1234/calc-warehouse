package com.company.service.impl;

import com.company.enums.TypeCounterparty;
import com.company.mapper.CounterpartyMapper;
import com.company.model.entity.Counterparty;
import com.company.model.form.CounterpartyForm;
import com.company.repository.CounterpartyRepository;
import com.company.service.CounterpartyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.company.util.ResponseBaseUtil.buildResponse;

@Service
@AllArgsConstructor
@Slf4j
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;
    private final CounterpartyMapper counterpartyMapper;

    @Override
    public ResponseEntity<?> addCounterparty(CounterpartyForm counterpartyForm) {
        try {
            counterpartyRepository.save(counterpartyMapper.counterpartyFormToCounterparty(counterpartyForm));
            log.info("Counterparty added successfully");
            return buildResponse(null, "Counterparty added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding counterparty {}", exception.getCause(), counterpartyForm);
            return buildResponse(null, "Counterparty added failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> updateCounterparty(CounterpartyForm counterpartyForm, int id) {
        try {
            counterpartyRepository
                    .updateCounterparty(
                            counterpartyForm.getName(),
                            counterpartyForm.getLastName(), TypeCounterparty.valueOf(counterpartyForm.getType()),
                            counterpartyForm.getPhone(), counterpartyForm.getAddress(),
                            counterpartyForm.getDescription(), id
                    );
            log.info("Counterparty updated successfully");
            return buildResponse(null, "Counterparty updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating counterparty {}", exception.getCause(), counterpartyForm);
            return buildResponse(null, "Counterparty updated failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> deleteCounterparty(int id) {
        try {
            counterpartyRepository.deleteCounterpartyByState(id);
            log.info("Counterparty deleted successfully");
            return buildResponse(null, "Counterparty deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting Counterparty {}", exception.getCause(), id);
            return buildResponse(null, "Counterparty deleted failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> listCounterparties() {
        List<Counterparty> counterpartyList = counterpartyRepository.getCounterpartyListByState();
        log.info("Get list of counterparties");
        return buildResponse(counterpartyList, "Counterparty list", true, 200);
    }

    @Override
    public ResponseEntity<?> getCounterparty(int id) {
        try {
            Optional<Counterparty> counterparty = counterpartyRepository.findById(id);
            if (counterparty.isEmpty()) {
                log.info("Counterparty not found");
                return buildResponse(null, "Counterparty not found", true, 404);
            }
            log.info("Counterparty found");
            return buildResponse(counterparty.get(), "Counterparty found", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong");
            return buildResponse(null, "Something went wrong", false, 500);
        }
    }


}
