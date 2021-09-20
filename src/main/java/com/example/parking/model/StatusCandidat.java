package com.example.parking.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StatusCandidat {

    ACCEPTE, REFUSE, ENCOURS;

    public static List<String> getValues() {
        return Stream.of(StatusCandidat.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
