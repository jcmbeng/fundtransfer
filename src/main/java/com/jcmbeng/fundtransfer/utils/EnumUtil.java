package com.jcmbeng.fundtransfer.utils;

import java.util.Optional;

public final class EnumUtil {

    private EnumUtil() { }

    /**
     * Checks if a given string is a valid constant in the specified Enum.
     *
     * @param <E>       the type of the Enum
     * @param enumClass the Enum class to check against
     * @param value     the string to validate
     * @return true if the string is a valid constant in the Enum, false otherwise
     */
    public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String value) {
        if (enumClass == null || value == null) {
            return false;
        }

        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Attempts to get an enum constant from a string value. Returns an Optional that contains
     * the enum constant if it exists, or an empty Optional if the string does not match any enum constant.
     *
     * @param <E>       the type of the Enum
     * @param enumClass the Enum class to retrieve the constant from
     * @param value     the string to convert to an enum constant
     * @return an Optional containing the matching enum constant, or empty if no match is found
     */
    public static <E extends Enum<E>> Optional<E> getEnumFromString(Class<E> enumClass, String value) {
        if (enumClass == null || value == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(Enum.valueOf(enumClass, value.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
