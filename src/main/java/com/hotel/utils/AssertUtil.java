package com.hotel.utils;

import java.util.Objects;

/**
 * @author 86183
 */
public class AssertUtil {
    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean equals(Object o1, Object o2) {
        return Objects.equals(o1,o2);
    }
}
