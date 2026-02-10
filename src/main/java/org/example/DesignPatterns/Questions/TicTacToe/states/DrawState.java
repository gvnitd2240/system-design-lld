package org.example.DesignPatterns.Questions.TicTacToe.states;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Game;
import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Player;
import org.example.DesignPatterns.Questions.TicTacToe.exceptions.InvalidMoveException;

public class DrawState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game is already over. It was a draw.");
    }
}
