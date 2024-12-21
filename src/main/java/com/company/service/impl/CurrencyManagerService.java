package com.company.service.impl;

import com.company.model.dto.CurrencyNBUDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.company.util.Constants.EXTERNAL_CURRENCY_URL;
import static com.company.util.Constants.USD;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyManagerService {

    private final WebClient webClient;

    public Double getUSDCurrency() {
        List<CurrencyNBUDTO> exchangeRates = webClient.get()
                .uri(EXTERNAL_CURRENCY_URL)
                .retrieve()
                .bodyToFlux(CurrencyNBUDTO.class)
                .collectList()
                .block();

        if (exchangeRates == null || exchangeRates.isEmpty()) {
            log.error("ExchangeRate is null or empty");
            return Double.NaN;
        }

        return exchangeRates.stream()
                .filter(this::isValidCurrency)
                .map(this::parsePrice)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("USD currency not found or invalid price"));
    }

    private boolean isValidCurrency(CurrencyNBUDTO currency) {
        return currency.getCode() != null && !currency.getCode().isEmpty()
                && currency.getCode().equalsIgnoreCase(USD)
                && currency.getCbPrice() != null && !currency.getCbPrice().isEmpty();
    }

    private Double parsePrice(CurrencyNBUDTO currency) {
        try {
            return Double.valueOf(currency.getCbPrice());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format for currency: " + currency.getCode(), e);
        }
    }
}
