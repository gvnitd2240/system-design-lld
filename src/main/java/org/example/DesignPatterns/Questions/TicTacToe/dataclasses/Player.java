package org.example.DesignPatterns.Questions.TicTacToe.dataclasses;

import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;

public class Player {
    private final String name;
    private final Symbol symbol;


    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
