package com.rylan;

public enum HandTypeEnum {
    HIGH_CARD("High Card"),
    PAIR("Pair"),
    TWO_PAIR("Two Pair"),
    THREE_OF_A_KIND("Three Of A Kind"),
    STRAIGHT("Straight"),
    FLUSH("Flush"),
    FULL_HOUSE("Full House"),
    FOUR_OF_A_KIND("Four Of A Kind"),
    STRAIGHT_FLUSH("Straight Flush"),
    ROYAL_FLUSH("Royal Flush");

    private final String name;

    private HandTypeEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
