package de.thw.oofb.models;

import lombok.Getter;

public enum Purpose {
    FOOD("Essen"), DRINKS("Trinken"), DEPOSIT("Einzahlung");

    @Getter
    String name;

    private Purpose(String name) {
        this.name = name;
    }
}