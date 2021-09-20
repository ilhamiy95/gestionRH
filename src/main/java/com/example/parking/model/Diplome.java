package com.example.parking.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Diplome {
    INGENIEUR, MASTER, LICENCE;

    public static List<String> getValues() {
        return Stream.of(Diplome.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
