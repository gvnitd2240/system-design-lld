package org.example.DesignPatterns.Questions.SnakeAndLadder;

import org.example.DesignPatterns.Questions.SnakeAndLadder.concreteclasses.Game;
import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.BoardEntity;
import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.Dice;
import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.Ladder;
import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.Snake;

import java.util.Arrays;
import java.util.List;

public class SnakeLadderDemo {
    public static void main(String[] args) {
        List<BoardEntity> boardEntities = List.of(
                new Snake(17, 7),
                new Snake(54, 34),
                new Snake(62, 19),
                new Snake(98, 79),
                new Ladder(3, 38),
                new Ladder(24, 33),
                new Ladder(42, 93),
                new Ladder(72, 84)
        );

        List<String> players = Arrays.asList("Alice", "Bob", "Charlie");
//        Game game  = new Game(new Dice(1, 6), 100, boardEntities, players);

        Game game = new Game.Builder().setBoard(100, boardEntities).setDice(new Dice(1, 6)).setPlayers(players).build();

        game.play();
    }
}
