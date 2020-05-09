package com.gagful.util;

import java.util.Collection;

public class CommonUtils {

    private CommonUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEmptyString(String str) {
        return str.isEmpty();
    }
}
