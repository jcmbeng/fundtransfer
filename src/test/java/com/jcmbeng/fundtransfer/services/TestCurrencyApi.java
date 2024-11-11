package com.jcmbeng.fundtransfer.services;

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
@Service(ExchangeRateProvider.TEST_CURRENCY_API)
public class TestCurrencyApi implements ExchangeRateService {

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
        return BigDecimal.valueOf (1.25);

    }
}
