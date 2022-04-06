package com.epam.retail.domain;

public enum Type {
    GOLD("Gold"),
    SILVER("Silver"),
    COPPER("Copper");

    public final String type;

    Type(String type) {
        this.type = type;
    }
}
