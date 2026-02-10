package org.example.DesignPatterns.Questions.TicTacToe.states;


import org.example.DesignPatterns.Questions.TicTacToe.concreateClasses.Game;
import org.example.DesignPatterns.Questions.TicTacToe.dataclasses.Player;
import org.example.DesignPatterns.Questions.TicTacToe.enums.GameStatus;
import org.example.DesignPatterns.Questions.TicTacToe.enums.Symbol;
import org.example.DesignPatterns.Questions.TicTacToe.exceptions.InvalidMoveException;

public class InProgressState implements GameState {
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        if (game.getStatus() != GameStatus.IN_PROGRESS) {
            throw new InvalidMoveException("Game is already over!");
        }
        if(!game.getBoard().isEmpty(row, col)){
            throw new InvalidMoveException(
                    "Cell (" + row + ", " + col + ") is already occupied"
            );
        }

        Player currentPlayer = game.getCurrentPlayer();

        game.getBoard().placeSymbol(row, col, currentPlayer.getSymbol());

        if(game.checkWin(row, col, currentPlayer.getSymbol())){
            game.setStatus((currentPlayer.getSymbol() == Symbol.X)
                    ? GameStatus.WINNER_X
                    : GameStatus.WINNER_0);

            game.setGameState(new WinnerState());
        } else if(game.getBoard().isFull()){
            game.setStatus(GameStatus.DRAW);
            game.setGameState(new DrawState());
        } else {
            // If the game is still in progress, switch players
            game.switchPlayer();
        }
    }
}
