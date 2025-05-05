# Ladder and Snake Game

Welcome to the *Ladder and Snake* game project! This is a Java console-based game implemented with an object-oriented design. It simulates the classic board game with creative features, smooth gameplay, and a clear separation of responsibilities between two developers.

---

## ✨ Game Summary

Ladder and Snake is a turn-based game played on a 10x10 board. Players take turns rolling a dice to move across the board, aiming to reach square 100. If they land at the bottom of a ladder, they climb up; if they land on a snake's head, they slide down. The first player to reach square 100 wins.

---

## 🌟 Features

- User input with error validation
- Animated dice roll simulation
- Snake and ladder jumps
- Bounce-back logic when exceeding square 100
- Tie-breaker logic to determine play order
- Text-based board position display
- ASCII art for clearer game tracking
- Beep sounds when hitting a snake or ladder (cross-platform-dependent)

---

## ⚙️ Project Structure

This project consists of the following classes:

### 1. `PlayLadderAndSnake.java`
- Contains the `main()` method
- Calls the `runGame()` method from the `GameSetup` class
- Has no logic implementation

### 2. `GameSetup.java`
Responsible for:
- Displaying the welcome message and developer names
- Prompting for the number of players (2 to 4), with 4 allowed attempts
- Taking custom names for players
- Handling dice rolls to determine play order
- Resolving ties and printing the final order
- Creating and launching the game instance

### 3. `LadderAndSnake.java`
Handles core game logic:
- Initializes board with snakes and ladders
- Rolls dice with delay and messages
- Moves players and applies bounce-back
- Handles ladder climb and snake slide with visual messages
- Prints player positions each round
- Detects game end and announces the winner

---

## 🔹 Developer Roles

### Fatemeh — Game Setup & Start Phase
- Welcome message and rules
- Prompt for number of players (with input validation)
- Limit invalid attempts to 4
- Initial turn ordering with tie resolution
- Initialize board configuration
- Final confirmation of player order

### Sarah — Gameplay Loop & End Phase
- Dice roll handling and movement logic
- Implement ladder and snake jump
- Bounce-back rule if over square 100
- Track player position each turn
- Detect and announce the winner
- Closing message

---

## 💼 How to Run the Project
1. Clone the project or open in IntelliJ or another Java IDE.
2. Ensure you have Java 8+ installed.
3. Run `PlayLadderAndSnake.java`.

---

## 🌈 Sample Output
```
Welcome to Ladder and Snake Game!
Developed by Fatemeh & Sarah

Enter the number of players (2 to 4): 3
Enter name for Player 1: Fatemeh
Enter name for Player 2: Sarah
Enter name for Player 3: Alex

- Now deciding which player will start playing:
Fatemeh got a dice value of 4
Sarah got a dice value of 6
Alex got a dice value of 6
A tie was achieved between Sarah and Alex. Attempting to break the tie
Sarah got a dice value of 3
Alex got a dice value of 5
- Reached final decision on the order of playing: Alex Sarah Fatemeh

Alex is on square 4 then up to 14
Sarah is on square 6
Fatemeh is on square 8 then up to 31
...
Fatemeh won the game!
Thank you for playing!
```

---

## 🎉 Thank You
We hope you enjoy our Ladder and Snake project. Feedback and contributions are welcome!

> Created with ❤️ by Fatemeh & Sarah

