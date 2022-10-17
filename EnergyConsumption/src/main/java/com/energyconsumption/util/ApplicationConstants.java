package com.energyconsumption.util;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

    private ApplicationConstants() {
        // class instantiation is not allowed
    }

    public static Map<Integer, String> createMapOfMonths() {
        Map<Integer, String> months = new HashMap<>();

        months.put(0, "JAN");
        months.put(1, "FEB");
        months.put(2, "MAR");
        months.put(3, "APR");
        months.put(4, "MAY");
        months.put(5, "JUN");
        months.put(6, "JUL");
        months.put(7, "AVG");
        months.put(8, "SEP");
        months.put(9, "OCT");
        months.put(10, "NOV");
        months.put(11, "DEC");

        return months;
    }

    /**
     * Method to return key by value from Map.
     *
     * @param map
     * @param value
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .findFirst().map(Map.Entry::getKey)
                .orElse(null);
    }

    /** e.g. Fri Feb 23 14:37:37 CET 2018 */
    public static final String DATE_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

}
