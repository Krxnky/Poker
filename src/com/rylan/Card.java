package com.rylan;

public class Card implements Comparable {
    private int value;
    private String suit;
    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;
    }
    public int getValue() {return value;}
    public String getSuit() {return suit;}
    public String getTextValue() {
        if(value>=2 && value<=10) {return Integer.toString(value);}
        else if(value==11) {return "J";}
        else if(value==12) {return "Q";}
        else if(value==13) {return "K";}
        else if(value==14) return "A";
        else {return "ERROR";}
    }
    public String toString() {
        return getTextValue() + getSuit();
    }
    @Override
    public int compareTo(Object other)
    {
        int thisVal = value == 1 ? 14 : value;
        int otherCardVal = ((Card)other).value;
        int otherVal =  otherCardVal == 1 ? 14 : otherCardVal;
        return thisVal - otherVal;
    }
}