package org.example.DesignPatterns.Questions.TicTacToe.concreateClasses;

import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Player;
import org.example.DesignPatterns.Questions.TicTacToe.enums.GameStatus;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;
import org.example.DesignPatterns.Questions.TicTacToe.observers.GameSubject;
import org.example.DesignPatterns.Questions.TicTacToe.states.GameState;
import org.example.DesignPatterns.Questions.TicTacToe.winningstrategies.ColWinningStrategy;
import org.example.DesignPatterns.Questions.TicTacToe.winningstrategies.DiagonalWinningStrategy;
import org.example.DesignPatterns.Questions.TicTacToe.winningstrategies.RowWinningStrategy;
import org.example.DesignPatterns.Questions.TicTacToe.winningstrategies.WinningStrategies;

import java.util.List;

public class Game extends GameSubject {
    private Board board;
    private Player currentPlayer; // Player 1 starts
    private GameStatus status;
    private List<WinningStrategies> winningStrategies;
    private final Player player1;
    private final Player player2;
    private GameState gameState;


    public Game(Player p1, Player p2, int boardSize){
        this.board = new Board(boardSize);
        this.currentPlayer = p1;
        this.player1 = p1;
        this.player2 = p2;
        this.status = GameStatus.IN_PROGRESS;
        this.winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()

        );
    }

    public synchronized void makeMove(int row, int col) {
        gameState.handleMove(this, currentPlayer, row, col);
    }

    public boolean checkWin(int row, int col, Symbol symbol){
        for(WinningStrategies winningStrategies1: this.winningStrategies){
            if(winningStrategies1.checkWin(board, row, col, symbol)){
                return true;
            }
        }

        return false;
    }

    public Board getBoard() { return board; }
    public Player getCurrentPlayer() { return currentPlayer;}
    public GameStatus getStatus() { return status; }

    public Player getWinner(){
        if(status == GameStatus.WINNER_X){
            return player1.getSymbol() == Symbol.X ? player1: player2;
        } else if (status == GameStatus.WINNER_0) {
            return player1.getSymbol() == Symbol.O ? player1: player2;
        }

        return null;
    }

    public void printBoard(){
        this.board.printBoard();
    }

    public void setStatus(GameStatus status) {
        this.status = status;
        if (status != GameStatus.IN_PROGRESS) {
            notifyObservers();
        }
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void switchPlayer() {
        this.currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}