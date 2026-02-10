package org.example.DesignPatterns.Questions.TicTacToe.winningstrategies;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Board;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;

public interface WinningStrategies {
    public boolean checkWin(Board board, int row, int col, Symbol symbol);
}
