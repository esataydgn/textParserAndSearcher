package com.text.search.schibsted.util;

public class StringUtils {

    public static boolean isNotBlank(String value) {
        return !isBlank(value);
    }

    public static boolean isBlank(String value) {
        return value == null || value.length() == 0;
    }

}
