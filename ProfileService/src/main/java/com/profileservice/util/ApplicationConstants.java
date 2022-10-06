package com.profileservice.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationConstants {

    private ApplicationConstants() {}

    public static Map<String, Integer> createMapOfMonths() {
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
}
