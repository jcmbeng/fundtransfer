package com.jcmbeng.fundtransfer.services.strategies.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcmbeng.fundtransfer.enums.ExchangeRateProvider;
import com.jcmbeng.fundtransfer.exceptions.ExchangeRateException;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.services.strategies.ExchangeRateService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.EXCHANGE_RATE_ERROR;

/**
 * A service implementation of {@link ExchangeRateService} that fetches exchange rates
 * from the FreeCurrencyAPI.
 *
 * <p>This service is registered with the {@link ExchangeRateProvider#FREE_CURRENCY_API} identifier.</p>
 */
@Service(ExchangeRateProvider.FREE_CURRENCY_API)
public class FreeCurrencyApi implements ExchangeRateService {

    /**
     * The API key for authenticating requests to the FreeCurrencyAPI.
     * Replace with your actual API key.
     */
    public static final String API_KEY = "fca_live_Q7OPBoEhgENZ20Zk4CFgpZdvdXQdxxBE90GkAp3v";

    /**
     * The base URL for the FreeCurrencyAPI, to be appended with the base currency.
     */
    private static final String API_URL = "https://api.freecurrencyapi.com/v1/latest?apikey=" + API_KEY + "&base_currency=";

    /**
     * Fetches the exchange rate between two currencies by querying the FreeCurrencyAPI.
     *
     * @param fromCurrency the currency code to convert from, e.g., "USD".
     * @param toCurrency   the currency code to convert to, e.g., "EUR".
     * @return a {@link BigDecimal} representing the exchange rate from `fromCurrency` to `toCurrency`.
     * @throws ExchangeRateException if an error occurs while fetching or parsing the exchange rate.
     */
    @Override
    public BigDecimal getExchangeRate(String fromCurrency, String toCurrency) throws ExchangeRateException {
        try {
            // Construct the full API URL with the base currency
            String urlString = API_URL + fromCurrency;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check if the response code is 200 (OK)
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new ExchangeRateException("Failed to retrieve exchange rate, HTTP code: " + connection.getResponseCode(), EXCHANGE_RATE_ERROR);
            }

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(response.toString(), Map.class);
            Map<String, Object> data = (Map<String, Object>) jsonMap.get("data");

            // Extract the exchange rate for the target currency
            if (data.containsKey(toCurrency)) {
                return new BigDecimal(data.get(toCurrency).toString());
            } else {
                throw new ExchangeRateException("Currency " + toCurrency + " not found in response data", EXCHANGE_RATE_ERROR);
            }

        } catch (InvalidOperationException | IOException exception) {
            throw new ExchangeRateException(exception.getMessage(), EXCHANGE_RATE_ERROR);
        }
    }
}
