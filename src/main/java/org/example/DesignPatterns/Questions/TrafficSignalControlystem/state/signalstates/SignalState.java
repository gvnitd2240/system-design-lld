package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.TrafficLight;

public interface SignalState {
    void handle(TrafficLight trafficLight);
}
