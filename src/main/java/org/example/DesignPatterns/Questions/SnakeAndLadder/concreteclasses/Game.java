package org.example.DesignPatterns.Questions.SnakeAndLadder.concreteclasses;

import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.BoardEntity;
import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.Dice;
import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {

    private SnakeAndLadderBoard snakeAndLadderBoard;
    private Dice dice;
    private Queue<Player> players;

    private Queue<Player> winners;


    private Game(Builder builder){
        this.snakeAndLadderBoard = builder.snakeAndLadderBoard;
        this.players = builder.players;
        this.dice = builder.dice;
        this.winners = new LinkedList<>();
    }
    
    private Game(Dice dice, int size, List<BoardEntity> boardEntities, List<String> players){
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(size, boardEntities);
        initialisePlayers(players);
        this.dice = dice;
    }

    private void initialisePlayers(List<String> players) {
        this.players = new LinkedList<>();
        for(String name: players){
            this.players.add(new Player(name));
        }
    }


    public void play(){
        if(players.size()<2){
            throw new IllegalArgumentException("Not sufficient players to play.");
        }

        System.out.println("Game started!");

        while(players.size()  > 1){
            Player currentPlayer = players.poll();
            boolean hasWon = takeTurn(currentPlayer);

            if(!hasWon){
                players.add(currentPlayer);
            } else{
                this.winners.add(currentPlayer);
                System.out.printf(
                        "%s has finished and is removed from the game.%n",
                        currentPlayer.getName()
                );
            }

        }

        System.out.println("Game Finished!");
        while(!winners.isEmpty()){
            Player won = winners.poll();
            System.out.printf("The winner is %s!%n", won.getName());
        }
        Player lastPlayer = players.peek();
        System.out.println("\nGame Finished!");
        System.out.printf("Last remaining player: %s%n", lastPlayer.getName());
    }

    public boolean takeTurn(Player player){
        int roll = dice.roll();

        System.out.printf("%n%s's turn. Rolled a %d.%n", player.getName(), roll);

        int currentPosition = player.getCurrentPosition();
        int nextPosition = currentPosition + roll;

        if(nextPosition > snakeAndLadderBoard.getSize()){
            System.out.printf(
                    "Oops, %s needs to land exactly on %d. Turn skipped.%n",
                    player.getName(), snakeAndLadderBoard.getSize()
            );
            return false;
        }

        //check for win.
        if(nextPosition == snakeAndLadderBoard.getSize()){
            player.setCurrentPosition(nextPosition);
            System.out.printf(
                    "Hooray! %s reached the final square %d and won!%n",
                    player.getName(), snakeAndLadderBoard.getSize()
            );
            return true;
        }

        int finalPosition = snakeAndLadderBoard.getFinalPosition(nextPosition);

        if(finalPosition > nextPosition){
            System.out.printf(
                    "Wow! %s found a ladder at %d and climbed to %d.%n",
                    player.getName(), nextPosition, finalPosition
            );
        } else if (finalPosition < nextPosition){
            System.out.printf(
                    "Oh no! %s was bitten by a snake at %d and slid down to %d.%n",
                    player.getName(), nextPosition, finalPosition
            );
        } else {
            System.out.printf(
                    "%s moved from %d to %d.%n",
                    player.getName(), currentPosition, finalPosition
            );
        }

        player.setCurrentPosition(finalPosition);

        // Extra turn for rolling 6
        if (roll == 6) {
            System.out.printf("%s rolled a 6 and gets another turn!%n", player.getName());
            takeTurn(player);
        }

        return false;
    }


    public static class Builder {
        private SnakeAndLadderBoard snakeAndLadderBoard;
        private Queue<Player> players;

        private Dice dice;

        public Builder setBoard(int size, List<BoardEntity> boardEntities){
            this.snakeAndLadderBoard = new SnakeAndLadderBoard(size, boardEntities);
            return this;
        }

        public Builder setPlayers(List<String> players){
            this.players = new LinkedList<>();
            for(String playerName: players){
                this.players.add(new Player(playerName));
            }

            return this;
        }

        public Builder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Game build() {
            if (snakeAndLadderBoard == null || players == null || dice == null) {
                throw new IllegalStateException("Board, Players, and Dice must be set.");
            }
            return new Game(this);
        }
    }
}
