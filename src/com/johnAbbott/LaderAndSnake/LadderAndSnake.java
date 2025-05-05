package com.johnAbbott.LaderAndSnake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LadderAndSnake {
    private ArrayList<Integer> board;
    private ArrayList<String> playersOrdered;
    private ArrayList<Integer> finalPosition;
    private int positionAfterMove;
    private int diceValue;
    private int playerIndex;
    private boolean gameOver = false;

    public LadderAndSnake(List<String> playerNames) {
        board = new ArrayList<>();
        playersOrdered = new ArrayList<>(playerNames);
        finalPosition = new ArrayList<>(Collections.nCopies(playerNames.size(), 0));
        initializeBoard();
    }

    private void initializeBoard() {
        Collections.addAll(board, 38, 2, 3, 14, 5, 6, 7, 8, 31, 10,
                11, 12, 13, 14, 15, 6, 17, 18, 19, 20, 42, 22, 23, 24, 25,
                26, 27, 84, 29, 30, 31, 32, 33, 34, 35, 44, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 30, 49, 50, 67, 52, 53, 54, 55,
                56, 57, 58, 59, 60, 61, 62, 63, 60, 65, 66, 67, 68, 69, 70,
                91, 72, 73, 74, 75, 76, 77, 78, 19, 100, 81, 82, 83, 84, 85,
                24, 87, 88, 89, 90, 91, 92, 68, 94, 95, 96, 76, 78, 99, 100);
    }

    // rolling the dice and printing the dice value
    public int flipDice(String player) {
        System.out.print(player + " is rolling the dice...");
        try {
            Thread.sleep(500); // Simulated dice roll delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        diceValue = (int) (Math.random() * 6) + 1;
        System.out.print(player + " got a dice value of " + diceValue + ";");
        return diceValue;
    }

    // moving the players on the board according to the dice value (not considering ladders and snakes)
    public void move(String player) {
        playerIndex = playersOrdered.indexOf(player);
        diceValue = flipDice(player);
        positionAfterMove = finalPosition.get(playerIndex) + diceValue;
    }

    // checking if the value exceeds 100 and implementing a bounce-back logic
    public void bounceBack() {
        if (positionAfterMove > 100) {
            positionAfterMove = 100 - (diceValue - (100 - finalPosition.get(playerIndex)));
        }
    }

    // applying the effect of ladders and snakes
    public void moveUpDown() {
        finalPosition.set(playerIndex, board.get(positionAfterMove - 1));
        if (Objects.equals(finalPosition.get(playerIndex), positionAfterMove)) {
            System.out.println(" now in square " + finalPosition.get(playerIndex));
        } else if (finalPosition.get(playerIndex) > positionAfterMove) {
            System.out.println(" went to square " + positionAfterMove + " then up to square " + finalPosition.get(playerIndex));
            System.out.print("\u0007"); // Beep sound
        } else {
            System.out.println(" went to square " + positionAfterMove + " then down to square " + finalPosition.get(playerIndex));
            System.out.print("\u0007"); // Beep sound
        }
    }

    // checking if anyone reached the sqaure 100 and won the game
    public void checkGameOver() {
        for (int i = 0; i < finalPosition.size(); i++) {
            if (finalPosition.get(i) == 100) {
                gameOver = true;
                System.out.println("\n " + playersOrdered.get(i) + " has reached square 100 and WON the game! \n");
                System.out.println("Thank you for playing Ladder and Snake! Goodbye! \n");
                break;
            }
        }
        if (!gameOver) {
            System.out.println("Game is not over; flipping again\n");
        }
    }

    // playing the game until someone reaches 100 and the game is over
    public void play() {
        while (!gameOver) {
            for (String player : playersOrdered) {
                move(player);
                bounceBack();
                moveUpDown();
            }
            printVisualBoard();
            checkGameOver();
        }
    }

    // Print a very basic visual representation of positions
    private void printVisualBoard() {
        System.out.println("Current Positions:");
        for (int i = 0; i < playersOrdered.size(); i++) {
            System.out.println("  " + playersOrdered.get(i) + " is on square " + finalPosition.get(i));
        }
        System.out.println();
    }

    public void setPlayersOrdered(List<String> playerOrder) {
        this.playersOrdered = new ArrayList<>(playerOrder);
    }

    public void resetGameState() {
        finalPosition = new ArrayList<>(Collections.nCopies(playersOrdered.size(), 0));
        positionAfterMove = 0;
        gameOver = false;
    }
}
