package com.emmanuelc.videorental.utils;

public class StringUtil {
    private StringUtil() {
    }

    public static boolean isNullOrBlank(final String s) {
        return s == null || s.isBlank();
    }
}
