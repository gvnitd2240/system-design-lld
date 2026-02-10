package org.example.DesignPatterns.Questions.TicTacToe.observers;

import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Game;

public interface GameObserver {
    public void update(Game game);

}
