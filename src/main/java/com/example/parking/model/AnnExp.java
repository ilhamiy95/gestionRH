package com.example.parking.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum AnnExp {
    DEBUTANT, PLUS_2ANS, PLUS_3ANS, PLUS_4ANS, PLUS_5ANS;

    public static List<String> getValues() {
        return Stream.of(AnnExp.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}

