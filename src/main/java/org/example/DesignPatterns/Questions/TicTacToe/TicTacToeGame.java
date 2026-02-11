package org.example.DesignPatterns.Questions.TicTacToe;

import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Player;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * https://algomaster.io/learn/lld/design-tic-tac-toe
 * 1.1 Functional Requirements
 * The game is played on a 3x3 grid.
 * Two players take alternate turns, identified by markers ‘X’ and ‘O’.
 * The game should detect and announce the winner.
 * The game should declare a draw if all cells are filled and no player has won.
 * The game should reject invalid moves and inform the player.
 * The system should maintain a scoreboard across multiple games.
 * Moves can be hardcoded in a driver/demo class to simulate gameplay.
 *
 * 1.2 Non-Functional Requirements
 * The design should follow object-oriented principles with clear responsibilities and separation of concerns.
 * The system should be modular and extensible to support future features like larger boards, AI opponent, move history, etc.
 * The game logic should be testable and easy to maintain.
 * The system should provide clear console output that reflects the current state of the game board.
 * */
public class TicTacToeGame {

    /***
     * 2. Identifying Core Entities
     *
     * 1. The game is played on a 3x3 grid. -> Board
     * 2. Each Square is a Cell: X, Y, piece
     * 3. Piece: X, O, EMPTY
     * 4. Player -> 1, 2
     * 4. The game processes moves and determines game outcomes.
     * 5. Game -> GameStatus, IN_PROGRESS, WINNER_X, WINNER_O, DRAW
     * 6. Scoreboard
     * 7. TicTacToeSystem
     */

    public static void main(String[] args) {
        TicTacToeSystem system = TicTacToeSystem.getInstance();

        Player alice = new Player("Alice", Symbol.X);
        Player bob = new Player("Bob", Symbol.O);

        // Game 1: Alice wins
        System.out.println("========== GAME 1 ==========");
        system.createGame(alice, bob);

        system.makeMove(alice, 0, 0);  // X at (0,0)
        system.makeMove(bob, 1, 0);    // O at (1,0)
        system.makeMove(alice, 0, 1);  // X at (0,1)
        system.makeMove(bob, 1, 1);    // O at (1,1)
        system.makeMove(alice, 0, 2);  // X at (0,2) - Alice wins!

        System.out.println("Game 1 Result: " + system.getGameStatus());

        // Game 2: Bob wins
        System.out.println("\n========== GAME 2 ==========");
        system.createGame(alice, bob);

        system.makeMove(alice, 0, 0);  // X at (0,0)
        system.makeMove(bob, 1, 1);    // O at (1,1) - center
        system.makeMove(alice, 0, 1);  // X at (0,1)
        system.makeMove(bob, 0, 2);    // O at (0,2)
        system.makeMove(alice, 2, 0);  // X at (2,0)
        system.makeMove(bob, 2, 2);    // O at (2,2) - Bob wins diagonal!

        System.out.println("Game 2 Result: " + system.getGameStatus());

        // Game 3: Draw
        System.out.println("\n========== GAME 3 ==========");
        system.createGame(alice, bob);

        system.makeMove(alice, 0, 0);  // X
        system.makeMove(bob, 0, 1);    // O
        system.makeMove(alice, 0, 2);  // X
        system.makeMove(bob, 1, 1);    // O
        system.makeMove(alice, 1, 0);  // X
        system.makeMove(bob, 1, 2);    // O
        system.makeMove(alice, 2, 1);  // X
        system.makeMove(bob, 2, 0);    // O
        system.makeMove(alice, 2, 2);  // X - Draw!

        System.out.println("Game 3 Result: " + system.getGameStatus());

        // Final scoreboard
        system.printScoreboard();
    }
}
