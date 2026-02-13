package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.TrafficLight;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;

public class RedState implements SignalState{
    @Override
    public void handle(TrafficLight trafficLight) {
        trafficLight.setColor(LightColor.RED);
        trafficLight.setNext(new RedState());
    }
}
