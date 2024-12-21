package com.company.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyManagerServiceTest {

    @Autowired
    private CurrencyManagerService currencyManagerService;

    @Test
    void getCurrencies() {
        long startTime = System.currentTimeMillis();
        Double usdCurrency = currencyManagerService.getUSDCurrency();
        System.out.println("Requested time : " + (System.currentTimeMillis() - startTime));

        assertNotNull(usdCurrency);
        System.out.println(usdCurrency);
    }

}