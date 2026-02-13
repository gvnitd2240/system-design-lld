package org.example.DesignPatterns.Questions.TrafficSignalControlPhaseSystem.enums;

public class TrafficLight {
    private String id;
    private LightColor color;
    private Direction facing;

    public TrafficLight(String id, Direction facing) {
        this.id = id;
        this.facing = facing;
        this.color = LightColor.RED;
    }

    public synchronized void setColor(LightColor color) {
        this.color = color;
        System.out.println("Light " + id + " (" + facing + ") -> " + color);
    }

    public LightColor getColor() {
        return color;
    }

    public String getId() {
        return id;
    }

    public Direction getFacing() {
        return facing;
    }

}
