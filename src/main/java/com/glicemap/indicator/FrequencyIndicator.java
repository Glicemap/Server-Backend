package com.glicemap.indicator;

public enum FrequencyIndicator {
    high("3"),
    medium("2"),
    low("1");

    private final String situation;

    FrequencyIndicator(String situation) {
        this.situation = situation;
    }

    public static FrequencyIndicator getEnum(String situation) {
        for (FrequencyIndicator s : FrequencyIndicator.values()) {
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
