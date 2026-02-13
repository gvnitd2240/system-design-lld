package org.example.DesignPatterns.Questions.TrafficSignalControlystem.observer;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.enums.Direction;

public class CentralMonitor implements TrafficObserver{
    @Override
    public void update(int intersectionId, Direction direction, LightColor color) {
        System.out.printf("[MONITOR] Intersection %d: Light for %s direction changed to %s.\n",
                intersectionId, direction, color);
    }
}
