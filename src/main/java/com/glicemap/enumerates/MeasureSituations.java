package com.glicemap.enumerates;

public enum MeasureSituations {
    ANTES_CAFE("Antes do café da manhã"),
    DEPOIS_CAFE("Depois do café da manhã"),
    ANTES_LANCHE_MANHA("Antes do lanche da manhã"),
    DEPOIS_LANCHE_MANHA("Depois do lanche da manhã"),
    ANTES_ALMOCO("Antes do almoço"),
    DEPOIS_ALMOCO("Depois do almoço"),
    ANTES_LANCHE_TARDE("Antes do lanche da tarde"),
    DEPOIS_LANCHE_TARDE("Depois do lanche da tarde"),
    ANTES_JANTAR("Antes do jantar"),
    DEPOIS_JANTAR("Depois do jantar"),
    ANTES_DORMIR("Antes de dormir");

    private final String situation;

    MeasureSituations(String situation) {
        this.situation = situation;
    }

    public static MeasureSituations getEnum(String situation) {
        for (MeasureSituations s : MeasureSituations.values()) {
            if (s.situation.equalsIgnoreCase(situation)) {
                return s;
            }
        }
        return null;
    }

    public String getString() {
        return situation;
    }
}
