package com.example.parking.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TypeContrat {
    CDI, CDD;

    public static List<String> getValues() {
        return Stream.of(TypeContrat.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
