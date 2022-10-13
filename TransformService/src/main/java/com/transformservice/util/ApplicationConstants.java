package com.transformservice.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationConstants {

    private ApplicationConstants() {}

    public static Map<String, Integer> createMapOfMonthsByMonth() {
        return Stream.of(new Object[][] {
                { "JAN", 0 },
                { "FEB", 1 },
                { "MAR", 2 },
                { "APR", 3 },
                { "MAY", 4 },
                { "JUN", 5 },
                { "JUL", 6 },
                { "AVG", 7 },
                { "SEP", 8 },
                { "OCT", 9 },
                { "NOV", 10 },
                { "DEC", 11 }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
    }

    public static Map<Integer, String> createMapOfMonths() {
        return Stream.of(new Object[][] {
                { 0, "JAN", },
                { 1, "FEB", },
                { 2, "MAR", },
                { 3, "APR", },
                { 4, "MAY", },
                { 5, "JUN", },
                { 6, "JUL", },
                { 7, "AVG", },
                { 8, "SEP", },
                { 9, "OCT", },
                { 10, "NOV", },
                { 11, "DEC", }
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
    }
}
