package org.example.DesignPatterns.Questions.SnakeAndLadder.concreteclasses;

import org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses.BoardEntity;

import java.util.HashMap;
import java.util.List;

public class SnakeAndLadderBoard {
    private final int size;
    private HashMap<Integer, Integer> snakesAndLadders;

    public SnakeAndLadderBoard(int size, List<BoardEntity> boardEntities) {
        this.size = size;
        this.snakesAndLadders = new HashMap<>();

        for (BoardEntity boardEntity: boardEntities){
            snakesAndLadders.put(boardEntity.getStartPosition(), boardEntity.getEndPosition());
        }
    }

    public int getSize() {
        return size;
    }

    public int getFinalPosition(int position) {
        return snakesAndLadders.getOrDefault(position, position);
    }
}
