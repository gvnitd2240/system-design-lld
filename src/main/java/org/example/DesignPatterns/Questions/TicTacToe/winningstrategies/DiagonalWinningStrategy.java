package org.example.DesignPatterns.Questions.TicTacToe.winningstrategies;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Board;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;


public class DiagonalWinningStrategy implements WinningStrategies {

    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();

        boolean mainDiagonalWin = true;
        for(int i =0 ;i<size;i++){
            if(board.getCell(i, i).getSymbol() != symbol){
                mainDiagonalWin = false;
                break;
            }
        }

        if(mainDiagonalWin) return true;

        for(int i = 0;i< size;i++){
            if(board.getCell(i, size - i - 1).getSymbol() != symbol){
                return false;
            };
        }

        return true;
    }
}