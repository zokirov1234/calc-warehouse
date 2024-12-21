package com.company.service.impl;

import com.company.mapper.CurrenciesMapper;
import com.company.model.entity.Currencies;
import com.company.model.form.CurrenciesForm;
import com.company.repository.CurrenciesRepository;
import com.company.service.CurrenciesService;
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
public class CurrenciesServiceImpl implements CurrenciesService {

    private final CurrenciesRepository currenciesRepository;
    private final CurrenciesMapper currenciesMapper;


    @Override
    public ResponseEntity<?> add(CurrenciesForm currenciesForm) {
        try {
            currenciesRepository.save(currenciesMapper.currenciesFormToCurrencies(currenciesForm));
            log.info("Currencies added successfully");
            return buildResponse(null, "Currencies added successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding currencies {}", exception.getCause(), currenciesForm);
            return buildResponse(null, "Currencies added failed", false, 500);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> update(int id, CurrenciesForm currenciesForm) {
        try {
            currenciesRepository.updateByName(currenciesForm.getName(), id);
            log.info("Currencies updated successfully");
            return buildResponse(null, "Currencies updated successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating currencies {}", exception.getCause(), currenciesForm);
            return buildResponse(null, "Currencies updated failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> delete(int currenciesId) {
        try {
            currenciesRepository.deleteById(currenciesId);
            log.info("Currencies deleted successfully");
            return buildResponse(null, "Currencies deleted successfully", true, 200);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting currencies {}", exception.getCause(), currenciesId);
            return buildResponse(null, "Currencies deleted failed", false, 500);
        }
    }

    @Override
    public ResponseEntity<?> get(int currenciesId) {
        Optional<Currencies> currencies = currenciesRepository.findById(currenciesId);
        if (currencies.isEmpty()) {
            log.info("Currencies id {} not found", currenciesId);
            return buildResponse(null, "Currencies not found", true, 404);
        }
        return buildResponse(currencies.get(), "Currencies found", true, 200);
    }

    @Override
    public ResponseEntity<?> list() {
        List<Currencies> allCurrencies = currenciesRepository.getList();
        return buildResponse(allCurrencies, "Currencies found", true, 200);
    }


}
