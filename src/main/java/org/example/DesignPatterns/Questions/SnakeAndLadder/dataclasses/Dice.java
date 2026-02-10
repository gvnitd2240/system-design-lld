package org.example.DesignPatterns.Questions.SnakeAndLadder.dataclasses;

public class Dice {
    public final int start;
    public final int end;

    public Dice(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int roll(){
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
