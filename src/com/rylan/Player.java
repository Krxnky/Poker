package com.rylan;

import java.util.*;

public class Player {
    private String name;
    private List<Card> hand;
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
    }
    public String getName() {return name;}
    public List<Card> getHand() {return hand;}

    public void printHand()
    {
        System.out.printf("%s - %s%n", name, hand);
        System.out.printf("That is a %s!%n", getHandType().type.getName());
    }

    // Card type functions
    private int isRoyalFlush()
    {
        return isStraightFlush() != -1 && (hand.get(0).getValue() == 10 && hand.get(0).getValue() == 14) ? 1 : -1;
    }

    private int isStraightFlush(){

        return (isStraight() != -1 && isFlush() != -1) ? hand.get(4).getValue() : -1;
    }

    private int isFourOfAKind()
    {
        HashMap<Integer, Integer> m = new LinkedHashMap<>();
        for (Card card : hand) {
            if (!m.containsKey(card.getValue())) {
                m.put(card.getValue(), 1);
            }
            else m.put(card.getValue(), m.get(card.getValue())+1);
        }

        Map.Entry<Integer, Integer> t = null;
        for(Map.Entry<Integer, Integer> en : m.entrySet())
        {
            if(en.getValue() == 4) t = en;
        }

        return m.containsValue(4) ? t.getValue() : -1;
    }

    private int isFullHouse()
    {
        HashMap<Integer, Integer> m = new LinkedHashMap<>();
        for (Card card : hand) {
            if (!m.containsKey(card.getValue())) {
                m.put(card.getValue(), 1);
            }
            else m.put(card.getValue(), m.get(card.getValue())+1);
        }
        Map.Entry<Integer, Integer> three = null;
        Map.Entry<Integer, Integer> two = null;
        for(Map.Entry<Integer, Integer> en : m.entrySet())
        {
            if(en.getValue() == 3) three = en;
            else if(en.getValue() == 2) two = en;
        }
        return m.containsValue(3) && m.containsValue(2) ? three.getKey() + two.getKey() : -1;
    }

    private int isFlush()
    {
        for(int i=0; i<hand.size()-1; i++)
        {
            if(!Objects.equals(hand.get(i + 1).getSuit(), hand.get(i).getSuit())) return -1;
        }
        return hand.get(4).getValue();
    }

    private int isStraight()
    {
        return (hand.get(4).getValue() - hand.get(0).getValue() == 4) ? hand.get(4).getValue() : -1;
    }

    private int isThreeOfAKind()
    {
        HashMap<Integer, Integer> m = new LinkedHashMap<>();
        for (Card card : hand) {
            if (!m.containsKey(card.getValue())) {
                m.put(card.getValue(), 1);
            }
            else m.put(card.getValue(), m.get(card.getValue())+1);
        }
        Map.Entry<Integer, Integer> t = null;
        for(Map.Entry<Integer, Integer> en : m.entrySet())
        {
            if(en.getValue() == 3) t = en;
        }
        return (t == null) ?  -1 : t.getKey();
    }

    private int isTwoPair()
    {
        int pairCount = 0;
        int value = 0;
        for(int i=0; i<hand.size()-1; i++)
        {
            if(hand.get(i+1).getValue() == hand.get(i).getValue())
            {
                pairCount++;
                value += hand.get(i).getValue();
                i++;
            }
        }
        return (pairCount == 2) ? value : -1;
    }

    private int isPair()
    {
        for(int i=0; i<hand.size()-1; i++)
        {
            if(hand.get(i+1).getValue() == hand.get(i).getValue()) return hand.get(i).getValue();
        }
        return -1;
    }

    public HandType getHandType()
    {
        if(isRoyalFlush() != -1) return new HandType(HandTypeEnum.ROYAL_FLUSH, isRoyalFlush());
        else if(isStraightFlush() != -1) return new HandType(HandTypeEnum.STRAIGHT_FLUSH, isStraightFlush());
        else if(isFourOfAKind() != -1) return new HandType(HandTypeEnum.FOUR_OF_A_KIND, isFourOfAKind());
        else if(isFullHouse() != -1) return new HandType(HandTypeEnum.FULL_HOUSE, isFullHouse());
        else if(isFlush() != -1) return new HandType(HandTypeEnum.FLUSH, isFlush());
        else if(isStraight() != -1) return new HandType(HandTypeEnum.STRAIGHT, isStraight());
        else if(isThreeOfAKind() != -1) return new HandType(HandTypeEnum.THREE_OF_A_KIND, isThreeOfAKind());
        else if(isTwoPair() != -1) return new HandType(HandTypeEnum.TWO_PAIR, isTwoPair());
        else if(isPair() != -1) return new HandType(HandTypeEnum.PAIR, isPair());
        else return new HandType(HandTypeEnum.HIGH_CARD, hand.get(4).getValue());
    }
}