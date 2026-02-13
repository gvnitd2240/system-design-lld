package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.TrafficLight;

public class YellowState implements SignalState{
    @Override
    public void handle(TrafficLight trafficLight) {
        trafficLight.setColor(LightColor.YELLOW);
        trafficLight.setNext(new RedState());
    }
}
