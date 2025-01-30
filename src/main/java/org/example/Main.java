package org.example;

import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Integer a = 500;
        System.out.println(LocalDate.now().toString());
        System.out.println(a);
        System.out.println(-a);
        List<Integer> ab = new ArrayList<>();
        List<Integer> d = new ArrayList<>();
        ab.add(5);
        ab.add(-5);
        ab.add(7);
        ab.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        d.add(8);
        HashMap<String, List<Integer>> hash = new HashMap<>(Map.of("11-04-2025",ab,"11-05-2025",ab));

        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


    }
}