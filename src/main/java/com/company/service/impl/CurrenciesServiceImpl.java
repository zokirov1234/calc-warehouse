package com.company.service.impl;

import com.company.mapper.CurrenciesMapper;
import com.company.model.dto.ResponseDto;
import com.company.model.entity.Currencies;
import com.company.model.form.CurrenciesForm;
import com.company.repository.CurrenciesRepository;
import com.company.service.CurrenciesService;
import com.company.util.BaseUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CurrenciesServiceImpl implements CurrenciesService {

    private final BaseUtil baseUtil;
    private final CurrenciesRepository currenciesRepository;
    private final CurrenciesMapper currenciesMapper;


    @Override
    public ResponseEntity<?> add(CurrenciesForm currenciesForm) {

        ResponseDto<?> responseDto;

        try {
            currenciesRepository.save(currenciesMapper.currenciesFormToCurrencies(currenciesForm));
            log.info("Currencies added successfully");
            responseDto = baseUtil.convertResponseDto(null, "Currencies added successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while adding currencies {}", exception.getCause(), currenciesForm);
            responseDto = baseUtil.convertResponseDto(null, "Currencies added failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> update(CurrenciesForm currenciesForm) {
        ResponseDto<?> responseDto;

        try {
            currenciesRepository.updateByName(currenciesForm.getName(), currenciesForm.getId());
            log.info("Currencies updated successfully");
            responseDto = baseUtil.convertResponseDto(null, "Currencies updated successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while updating currencies {}", exception.getCause(), currenciesForm);
            responseDto = baseUtil.convertResponseDto(null, "Currencies updated failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> delete(int currenciesId) {
        ResponseDto<?> responseDto;

        try {
            currenciesRepository.deleteById(currenciesId);
            log.info("Currencies deleted successfully");
            responseDto = baseUtil.convertResponseDto(null, "Currencies deleted successfully", true, 200);
            return ResponseEntity.status(200).body(responseDto);
        } catch (Exception exception) {
            log.error("Something went wrong : {} while deleting currencies {}", exception.getCause(), currenciesId);
            responseDto = baseUtil.convertResponseDto(null, "Currencies deleted failed", false, 500);
            return ResponseEntity.status(500).body(responseDto);
        }
    }

    @Override
    public ResponseEntity<?> get(int currenciesId) {
        ResponseDto<?> responseDto;

        Optional<Currencies> currencies = currenciesRepository.findById(currenciesId);

        if (currencies.isEmpty()) {
            log.info("Currencies id {} not found", currenciesId);
            responseDto = baseUtil.convertResponseDto(null, "Currencies not found", true, 404);
            return ResponseEntity.status(404).body(responseDto);
        }

        log.info("Currencies id {} found", currenciesId);
        responseDto = baseUtil.convertResponseDto(currencies.get(), "Currencies found", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }

    @Override
    public ResponseEntity<?> list() {

        ResponseDto<?> responseDto;

        List<Currencies> allCurrencies = currenciesRepository.getList();

        responseDto = baseUtil.convertResponseDto(allCurrencies, "Currencies found", true, 200);
        return ResponseEntity.status(200).body(responseDto);
    }


}
