package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.TrafficLight;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;

public class GreenState implements SignalState{
    @Override
    public void handle(TrafficLight trafficLight) {
        trafficLight.setColor(LightColor.GREEN);
        trafficLight.setNext(new YellowState());
    }
}
