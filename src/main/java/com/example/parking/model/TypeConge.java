package com.example.parking.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TypeConge {
    PAYE, SANS_SOLDE;

    public static List<String> getValues() {
        return Stream.of(TypeConge.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
