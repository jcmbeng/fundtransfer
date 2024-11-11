package com.jcmbeng.fundtransfer.utils;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.jcmbeng.fundtransfer.consts.Constants.OPERATION_SCALE;
import static com.jcmbeng.fundtransfer.consts.Constants.ROUNDING_MODE;

public final class BigDecimalUtil {

    private BigDecimalUtil() { }

    /**
     * Checks if two BigDecimal values are equal.
     *
     * @param first  the first BigDecimal
     * @param second the second BigDecimal
     * @return true if both values are equal, false otherwise
     */
    public static boolean isEqual(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            return false;
        }
        return first.compareTo(second) == 0;
    }

    /**
     * Checks if the first BigDecimal is greater than the second.
     *
     * @param first  the first BigDecimal
     * @param second the second BigDecimal
     * @return true if the first value is greater than the second, false otherwise
     */
    public static boolean isGreaterThan(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return first.compareTo(second) > 0;
    }

    /**
     * Checks if the first BigDecimal is less than the second.
     *
     * @param first  the first BigDecimal
     * @param second the second BigDecimal
     * @return true if the first value is less than the second, false otherwise
     */
    public static boolean isLessThan(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return first.compareTo(second) < 0;
    }

    /**
     * Checks if the first BigDecimal is greater than or equal to the second.
     *
     * @param first  the first BigDecimal
     * @param second the second BigDecimal
     * @return true if the first value is greater than or equal to the second, false otherwise
     */
    public static boolean isGreaterThanOrEqualTo(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return first.compareTo(second) >= 0;
    }

    /**
     * Checks if the first BigDecimal is less than or equal to the second.
     *
     * @param first  the first BigDecimal
     * @param second the second BigDecimal
     * @return true if the first value is less than or equal to the second, false otherwise
     */
    public static boolean isLessThanOrEqualTo(BigDecimal first, BigDecimal second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return first.compareTo(second) <= 0;
    }

    /**
     * Checks if a BigDecimal is zero.
     *
     * @param value the BigDecimal to check
     * @return true if the value is zero, false otherwise
     */
    public static boolean isZero(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Checks if a BigDecimal is positive.
     *
     * @param value the BigDecimal to check
     * @return true if the value is greater than zero, false otherwise
     */
    public static boolean isPositive(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Checks if a BigDecimal is negative.
     *
     * @param value the BigDecimal to check
     * @return true if the value is less than zero, false otherwise
     */
    public static boolean isNegative(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) < 0;
    }

    public static BigDecimal multiply(BigDecimal first, BigDecimal second){
        return (first.multiply (second)).setScale (OPERATION_SCALE, ROUNDING_MODE);
    }
}

