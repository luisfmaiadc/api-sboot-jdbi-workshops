package com.portfolio.luisfmdc.api_sboot_jdbi_workshops.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum UF {

    AC,
    AL,
    AP,
    AM,
    BA,
    CE,
    ES,
    GO,
    MA,
    MT,
    MS,
    MG,
    PA,
    PB,
    PR,
    PE,
    PI,
    RJ,
    RN,
    RS,
    RO,
    RR,
    SC,
    SP,
    SE,
    TO;

    private static final Set<String> SIGLAS = Arrays.stream(values()).map(Enum::name).collect(Collectors.toSet());
    public static boolean isValid(String uf) {
        return SIGLAS.contains(uf.toUpperCase());
    }
}
