package org.example.DesignPatterns.Questions.TicTacToe;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Game;
import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Player;
import org.example.DesignPatterns.Questions.TicTacToe.enums.GameStatus;
import org.example.DesignPatterns.Questions.TicTacToe.observers.ScoreCard;

public class TicTacToeSystem{
    private static volatile TicTacToeSystem instance;
    private static final Object lock = new Object();

    private final ScoreCard scoreCard;
    private Game currentGame;

    public TicTacToeSystem() {
        this.scoreCard = new ScoreCard();
    }

    public static TicTacToeSystem getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new TicTacToeSystem();
                }
            }
        }
        return instance;
    }

    public Game createGame(Player p1, Player p2){
        currentGame = new Game(p1, p2, 3);
        currentGame.addObserver(scoreCard);
        System.out.println("New game started: " + p1.getName() +
                " vs " + p2.getName());
        return currentGame;
    }

    public void makeMove(Player player, int row, int col){
        if (currentGame == null) {
            throw new IllegalStateException("No active game. Call createGame first.");
        }
        System.out.println(player.getName() + " plays at (" + row + ", " + col + ")");
        currentGame.makeMove(row, col);
        currentGame.printBoard();
    }

    public GameStatus getGameStatus() {
        if (currentGame == null) {
            throw new IllegalStateException("No active game.");
        }
        return currentGame.getStatus();
    }

    public void printScoreboard() {
        scoreCard.printScoreboard();
    }

    // For testing: reset the singleton
    public static void resetInstance() {
        synchronized (lock) {
            instance = null;
        }
    }

}
