package com.jcmbeng.fundtransfer.factories;

import com.jcmbeng.fundtransfer.exceptions.ExchangeRateException;
import com.jcmbeng.fundtransfer.services.strategies.ExchangeRateService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

import static com.jcmbeng.fundtransfer.consts.Constants.OPERATION_SCALE;
import static com.jcmbeng.fundtransfer.consts.Constants.ROUNDING_MODE;
import static com.jcmbeng.fundtransfer.enums.ExchangeRateProvider.TEST_CURRENCY_API;

@Component
public class ExchangeRateFactory {

    /**
     * A map that stores the {@link ExchangeRateService} implementations keyed by exchangeRate type.
     */
    private final Map<String, ExchangeRateService> exchangeRateServiceMap;

    /**
     * Constructs a {@code ExchangeRateFactory} with the given map of exchangeRate services.
     *
     * @param exchangeRateServices a {@link Map} where the key is the exchangeRate type (e.g., "email", "sms")
     *                             and the value is the corresponding {@link ExchangeRateService} implementation
     */
    public ExchangeRateFactory (Map<String, ExchangeRateService> exchangeRateServices) {
        this.exchangeRateServiceMap = exchangeRateServices;
    }

    /**
     * Retrieves the {@link ExchangeRateService} implementation for the specified exchangeRate type.
     *
     * @param exchangeRateType a {@link String} representing the type of exchangeRate (e.g., "email", "sms")
     * @return the corresponding {@link ExchangeRateService} implementation
     * @throws RuntimeException if the exchangeRate type is not supported or no implementation is found
     */
    private ExchangeRateService getExchangeRateService (String exchangeRateType) {
        ExchangeRateService exchangeRateService = exchangeRateServiceMap.get (exchangeRateType);
        if (exchangeRateService == null) {
            throw new RuntimeException ("Unsupported Exchange Rate type");
        }
        return exchangeRateService;
    }


    public BigDecimal getExchangeRate (String exchangeRateType, String fromCurrency, String toCurrency) throws ExchangeRateException {
        if(exchangeRateType.equals (TEST_CURRENCY_API)){
            return BigDecimal.valueOf (1.25);
        }
        ExchangeRateService exchangeRateService = getExchangeRateService(exchangeRateType);
       return exchangeRateService.getExchangeRate ( fromCurrency,  toCurrency).setScale (OPERATION_SCALE, ROUNDING_MODE);
    }
}
