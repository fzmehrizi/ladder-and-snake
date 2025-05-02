import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class LadderAndSnake {
    private ArrayList<Integer> board;
    private ArrayList<String> playersOrdered;
    private int positionAfterMove;
    private ArrayList<Integer> finalPosition;
    boolean gameOver = false;
    private int diceValue;
    private int playerIndex;


    // to be replaced with Fatemeh's code (added for the purpose of testing my code)
    public LadderAndSnake() {
        board = new ArrayList<>();
        playersOrdered = new ArrayList<>();
        finalPosition = new ArrayList<>();
        Collections.addAll(playersOrdered, "Player 1", "Player 2", "Player 3");
        for (int i = 0; i < playersOrdered.size(); i++) {
            positionAfterMove = 0;
            finalPosition.add(0);
        }
        Collections.addAll(board, 38, 2, 3, 14, 5, 6, 7, 8, 31, 10, 11, 12, 13, 14, 15, 6, 17, 18, 19, 20, 42, 22, 23, 24, 25, 26, 27, 84, 29, 30, 31, 32, 33, 34, 35, 44, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 30, 49, 50, 67, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 60, 65, 66, 67, 68, 69, 70, 91, 72, 73, 74, 75, 76, 77, 78, 19, 100, 81, 82, 83, 84, 85, 24, 87, 88, 89, 90, 91, 92, 68, 94, 95, 96, 76, 78, 99, 100);
        play();
    }

    // rolling the dice and printing the dice value
    public int flipDice(String player) {
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
        } else {
            System.out.println(" went to square " + positionAfterMove + " then down to square " + finalPosition.get(playerIndex));
        }
    }

    // checking if anyone reached the sqaure 100 and won the game
    public void checkGameOver() {
        for (int i = 0; i < finalPosition.size(); i++) {
            if (finalPosition.get(i) == 100) {
                gameOver = true;
                System.out.println(playersOrdered.get(i) + " won the game!");
                break;
            }
        }
        if (!gameOver) {
            System.out.println("Game is not over; flipping again\n");
        }
    }

    // playing the game until someone reaches 100 and game is over
    public void play() {
        while (!gameOver) {
            for (String player : playersOrdered) {
                move(player);
                bounceBack();
                moveUpDown();
            }
            checkGameOver();
        }
    }
}
