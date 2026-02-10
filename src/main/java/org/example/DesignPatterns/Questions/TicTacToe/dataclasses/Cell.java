package org.example.DesignPatterns.Questions.TicTacToe.dataclasses;

import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;

public class Cell {
    private Symbol symbol;

    public Cell(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty(){
        return this.symbol == Symbol.EMPTY;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
