package org.example.DesignPatterns.Questions.TicTacToe.winningstrategies;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Board;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;


public class RowWinningStrategy implements WinningStrategies {

    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        for(int i =0 ;i<board.getSize();i++){
            if(board.getCell(row, i).getSymbol() != symbol){
                return false;
            }
        }

        return true;
    }
}
