package com.rylan;

public class HandType
{
    HandTypeEnum type;
    int highestValue;
    public HandType(HandTypeEnum type, int highestValue)
    {
        this.type = type;
        this.highestValue = highestValue;
    }

    public HandTypeEnum getType() {
        return type;
    }

    public int getHighestValue() {
        return highestValue;
    }
}
