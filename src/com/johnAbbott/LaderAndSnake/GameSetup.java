package com.johnAbbott.LaderAndSnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameSetup {
    public static void runGame() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int numPlayers = 0;

        while (attempts < 4) {
            System.out.print("Enter the number of players (2 to 4): ");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine());
                if (numPlayers >= 2 && numPlayers <= 4) break;
            } catch (NumberFormatException ignored) {}
            attempts++;
            System.out.println("Bad Attempt " + attempts + " - Invalid # of players.");
        }

        if (attempts == 4) {
            System.out.println("Bad Attempt 4! You have exhausted all your chances. The program will terminate!");
            return;
        }

        List<String> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine().trim();
            players.add(name + "  ");
        }

        printWelcomeMessage(players);

        int[] rolls = new int[numPlayers];
        Random random = new Random();

        System.out.println("- Now deciding which player will start playing:");
        for (int i = 0; i < players.size(); i++) {
            rolls[i] = random.nextInt(6) + 1;
            System.out.println(players.get(i) + " got a dice value of " + rolls[i]);
        }

        // Resolve tie-breakers
        boolean tie;
        do {
            tie = false;
            for (int i = 0; i < rolls.length; i++) {
                for (int j = i + 1; j < rolls.length; j++) {
                    if (rolls[i] == rolls[j]) {
                        tie = true;
                        System.out.println("A tie was achieved between " + players.get(i) + " and "
                                + players.get(j) + ". Attempting to break the tie");
                        rolls[i] = random.nextInt(6) + 1;
                        rolls[j] = random.nextInt(6) + 1;
                        System.out.println(players.get(i) + " got a dice value of " + rolls[i]);
                        System.out.println(players.get(j) + " got a dice value of " + rolls[j]);
                    }
                }
            }
        } while (tie);

        // Sort players by their roll values
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (rolls[j] > rolls[i]) {
                    int temp = rolls[i];
                    rolls[i] = rolls[j];
                    rolls[j] = temp;
                    String tempName = players.get(i);
                    players.set(i, players.get(j));
                    players.set(j, tempName);
                }
            }
        }

        System.out.print("- Reached final decision on the order of playing: ");
        for (String p : players) System.out.print(p + " ");
        System.out.println("\n");

        LadderAndSnake game = new LadderAndSnake(players);
        game.resetGameState();
        game.play();
    }

    private static void printWelcomeMessage(List<String> playersOrdered) {
        System.out.println("\n Welcome to Ladder and Snake Game!");
        System.out.println(" Developed by Fatemeh & Sarah\n");
        System.out.println(" Game is played by " + playersOrdered.size() + " players");
        System.out.println(" " +
                "Let’s begin the game!\n");
    }
}

