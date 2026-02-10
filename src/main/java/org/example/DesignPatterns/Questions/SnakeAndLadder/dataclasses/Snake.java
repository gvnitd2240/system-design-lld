package org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses;

public class Snake extends BoardEntity{


    public Snake(int startPosition, int endPosition) {
        super(startPosition, endPosition);

        if(startPosition<=endPosition){
            throw new IllegalArgumentException(
                    "Snake head must be at a higher position than its tail."
            );
        }
    }
}

