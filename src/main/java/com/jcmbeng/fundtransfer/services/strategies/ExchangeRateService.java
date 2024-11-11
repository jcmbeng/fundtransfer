package com.jcmbeng.fundtransfer.services.strategies;

import com.jcmbeng.fundtransfer.exceptions.ExchangeRateException;

import java.math.BigDecimal;

/**
 * Service interface for fetching exchange rates between currencies.
 * <p>
 * This interface defines a method for obtaining exchange rates from one currency to another.
 * Implementations may use different external providers or APIs for retrieving the rates.
 * </p>
 */
public interface ExchangeRateService {

    /**
     * Retrieves the exchange rate between two specified currencies.
     *
     * @param fromCurrency the ISO 4217 code of the currency to convert from (e.g., "USD").
     * @param toCurrency   the ISO 4217 code of the currency to convert to (e.g., "EUR").
     * @return a {@link BigDecimal} representing the exchange rate from `fromCurrency` to `toCurrency`.
     * @throws ExchangeRateException if an error occurs while fetching the exchange rate,
     *                               such as connectivity issues or an invalid response from the provider.
     */
    BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws ExchangeRateException;
}
