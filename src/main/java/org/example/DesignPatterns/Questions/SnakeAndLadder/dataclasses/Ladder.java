package org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses;

public class Ladder extends BoardEntity {

    public Ladder(int startPosition, int endPosition) {
        super(startPosition, endPosition);

        if(endPosition<=startPosition){
            throw new IllegalArgumentException(
                    "Snake head must be at a higher position than its tail."
            );
        }
    }
}
