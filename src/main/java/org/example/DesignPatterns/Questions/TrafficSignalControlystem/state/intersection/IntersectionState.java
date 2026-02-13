package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.intersection;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.IntersectionController;

public interface IntersectionState {
    void handle(IntersectionController context) throws InterruptedException;
}
