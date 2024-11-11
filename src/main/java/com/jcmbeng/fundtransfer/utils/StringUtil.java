package com.jcmbeng.fundtransfer.utils;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

import java.math.BigDecimal;
import java.util.UUID;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.RESOURCE_WITH_ID_NOT_FOUND;

/**
 * Utility class for string manipulations related to resource not found messages.
 *
 * <p>This class provides methods to generate formatted messages for resources
 * that cannot be found based on their entity type and identifier.</p>
 */
public class StringUtil {

    /**
     * Generates a resource not found message for a given entity and its ID.
     *
     * @param entity the name of the entity (e.g., "Client", "Account")
     * @param id     the identifier of the resource (as a String)
     * @return a formatted message indicating that the resource with the specified ID was not found
     * @see CustomMessage
     */
    public static String resourceNotFoundWithId(String entity, String id) {
        return String.format(RESOURCE_WITH_ID_NOT_FOUND.getMessage(), entity, id);
    }

    /**
     * Generates a resource not found message for a given entity and its ID.
     *
     * @param entity the name of the entity (e.g., "Client", "Account")
     * @param id     the identifier of the resource (as a Long)
     * @return a formatted message indicating that the resource with the specified ID was not found
     * @see CustomMessage
     */
    public static String resourceNotFoundWithId(String entity, Long id) {
        return String.format(RESOURCE_WITH_ID_NOT_FOUND.getMessage(), entity, id);
    }

    public static String referenceGenerator(String prefix){
        return prefix + "-" + (UUID.randomUUID ().toString ().split ("-")[0]).toUpperCase ();
    }

    public static BigDecimal getExchangeRate(String fromCurrency, String toCurrency){
        if(fromCurrency.equals (toCurrency))
            return new BigDecimal (1);
        else
            return new BigDecimal (1);

    }
}
