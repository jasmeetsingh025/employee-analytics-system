package com.example.v2;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Rough {
    
    public static void main(String[] args) {
        String str = "Hello, World!";

        str.chars().mapToObj(s -> (char) s)
        .collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
        .filter(res -> res.getValue() > 1)
        .collect(Collectors.toList());
    }

}
