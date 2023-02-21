package com.rylan;

import java.util.Collections;
import java.util.Scanner;

public class Poker {

    public static Deck deck = new Deck();
    public static Scanner scan = new Scanner(System.in);

    public static int roundNumber = 1;
    public static int humanWins = 0;
    public static int cpuWins = 0;
    public static boolean stillPlaying = true;

    public static void main(String[] args) {
        System.out.println("Welcome to Poker! Please input your name:");

        String playerName = scan.nextLine();
        while(stillPlaying)
        {
            startGame(playerName);
            System.out.println("Do you want to play again? (y/n)");
            String ans = scan.nextLine();
            if(ans.equalsIgnoreCase("n")) stillPlaying = false;
        }
        System.out.printf("The score was: %s to %s%n", humanWins, cpuWins);
    }

    public static void startGame(String playerName)
    {
        clearConsole();
        deck = new Deck();
        deck.riffleShuffle();

//        Player debug = new Player("Debug");
//        List<Card> dHand = debug.getHand();
//        dHand.add(new Card(3, "♥"));
//        dHand.add(new Card(3, "♥"));
//        dHand.add(new Card(3, "♥"));
//        dHand.add(new Card(5, "♥"));
//        dHand.add(new Card(4, "♥"));
//        debug.printHand();

        System.out.printf("Round %s: %s to %s%n", roundNumber, humanWins, cpuWins);

        Player human = new Player(playerName);
        Player cpu = new Player("CPU");

        for(int i=0; i<5; i++)
        {
            human.getHand().add(deck.getDeck().pop());
            cpu.getHand().add(deck.getDeck().pop());
        }

        Collections.sort(human.getHand());
        Collections.sort(cpu.getHand());

        human.printHand();

        System.out.println("Which cards would you like to replace? (indexes ex. 012)");
        String indexesToReplace = scan.nextLine();
        if(indexesToReplace.length() > 0)
        {
            for(String indexStr : indexesToReplace.split(""))
            {

                int index = Integer.parseInt(indexStr);
                Card nCard = deck.getDeck().pop();
                Card oCard = human.getHand().get(index);
                System.out.printf("You discard %s and draw %s%n", oCard.getTextValue() + oCard.getSuit(), nCard.getTextValue() + nCard.getSuit());
                human.getHand().set(index, nCard);
            }
        }

        Collections.sort(human.getHand());
        System.out.printf("%s, here is your hand after replacement!%n", playerName);
        human.printHand();

        System.out.println();
        cpu.printHand();

        Player winner = declareWinner(human, cpu);
        if(winner == null)
        {
            System.out.println("Tie! No one wins!");
        }
        else if(winner == human)
        {
            System.out.println("You win!");
            humanWins++;
        }
        else
        {
            System.out.println("CPU won!");
            cpuWins++;
        }

    }

    public static Player declareWinner(Player p1, Player p2)
    {
        if(p1.getHandType().type == p2.getHandType().type)
        {
            return (p1.getHandType().highestValue == p2.getHandType().highestValue) ? null : (p1.getHandType().highestValue > p2.getHandType().highestValue) ? p1 : p2;
        }
        return (HandTypeEnum.valueOf(p1.getHandType().type.toString()).ordinal() > HandTypeEnum.valueOf(p2.getHandType().type.toString()).ordinal()) ? p1 : p2;
    }

    public static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
