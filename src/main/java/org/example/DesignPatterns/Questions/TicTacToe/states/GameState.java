package org.example.DesignPatterns.Questions.TicTacToe.states;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Game;
import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Player;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);
}
