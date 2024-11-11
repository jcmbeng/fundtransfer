package com.jcmbeng.fundtransfer.services.strategies.impl;

import com.jcmbeng.fundtransfer.enums.ExchangeRateProvider;
import com.jcmbeng.fundtransfer.exceptions.ExchangeRateException;
import com.jcmbeng.fundtransfer.services.strategies.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.EXCHANGE_RATE_ERROR;

/**
 * Dummy implementation of {@link ExchangeRateService} that simulates exchange rate retrieval.
 * This service is registered with {@link ExchangeRateProvider#DUMMY_CURRENCY_API}.
 */
@Service(ExchangeRateProvider.DUMMY_CURRENCY_API)
public class DummyCurrencyApi implements ExchangeRateService {

    /**
     * Retrieves a simulated exchange rate for converting between currencies.
     * Generates a random number to either return a specific rate or throw an {@link ExchangeRateException}.
     *
     * @param fromCurrency the currency code to convert from, e.g., "USD".
     * @param toCurrency   the currency code to convert to, e.g., "EUR".
     * @return a {@link BigDecimal} representing the exchange rate from `fromCurrency` to `toCurrency`.
     * @throws ExchangeRateException if the exchange rate retrieval fails or if the target currency is unavailable.
     */
    @Override
    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws ExchangeRateException {
        Random random = new Random();
        int randomNumber = random.nextInt(5) + 1;

        switch (randomNumber) {
            case 1:
                return BigDecimal.valueOf (1);
            case 2:
                return BigDecimal.valueOf (1.25);
            case 3:
                return BigDecimal.valueOf (0.75);
            case 4:
                throw new ExchangeRateException(
                        "Failed to retrieve exchange rate, HTTP code: " + HttpStatus.NOT_FOUND,
                        EXCHANGE_RATE_ERROR
                );
            case 5:
                throw new ExchangeRateException(
                        "Currency " + toCurrency + " not found in response data",
                        EXCHANGE_RATE_ERROR
                );
            default:
                return BigDecimal.valueOf (2);
        }
    }
}
