package org.example.DesignPatterns.Questions.TrafficSignalControlPhaseSystem.enums;

import java.util.List;

public class Movement {
    private MovementType movementType;
    private final List<TrafficLight> lights;

    public Movement(MovementType movementType, List<TrafficLight> lights) {
        this.movementType = movementType;
        this.lights = lights;
    }

    public MovementType getType() {
        return movementType;
    }

    public List<TrafficLight> getLights() {
        return lights;
    }

    public void activateGreen() {
        lights.forEach(l -> l.setColor(LightColor.GREEN));
    }

    public void activateYellow() {
        lights.forEach(l -> l.setColor(LightColor.YELLOW));
    }

    public void activateRed() {
        lights.forEach(l -> l.setColor(LightColor.RED));
    }

}
