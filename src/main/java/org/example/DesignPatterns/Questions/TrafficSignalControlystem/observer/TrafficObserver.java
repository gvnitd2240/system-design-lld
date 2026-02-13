package org.example.DesignPatterns.Questions.TrafficSignalControlystem.observer;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.enums.Direction;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;

public interface TrafficObserver {
    void update(int intersectionId, Direction direction, LightColor color);
}
