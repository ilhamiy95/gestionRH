package com.example.parking.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum StatusConge {

    ACCEPTE, REFUSE, EN_COURS;

    public static List<String> getValues() {
        return Stream.of(StatusConge.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
