package org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.enums.Direction;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.observer.TrafficObserver;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates.GreenState;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates.RedState;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.signalstates.SignalState;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
    private final Direction direction;
    private SignalState current;
    private SignalState next;
    private LightColor color;
    private final List<TrafficObserver> observers = new ArrayList<>();
    private final int intersectionId;

    public TrafficLight(int intersectionId, Direction direction) {
        this.direction = direction;
        this.intersectionId = intersectionId;
        this.color = LightColor.RED;
        this.current = new RedState();
        this.current.handle(this);
    }

    public void setCurrent(SignalState current) {
        this.current = current;
    }

    public void setNext(SignalState next) {
        this.next = next;
    }

    public void setColor(LightColor color) {
        if(this.color != color){
            this.color = color;
            notify();
        }
    }

    public void addObserver(TrafficObserver observer){
        this.observers.add(observer);
    }

    //is is called by the IntersectionController to initiate a G-Y-R cycle
    public void startGreen(){
        this.current = new GreenState();
        this.color = LightColor.GREEN;
    }

    public void transition(){
        this.current= this.next;
        this.current.handle(this);
    }

    public void notifyObservers(){
        for (TrafficObserver observer : observers) {
            observer.update(intersectionId, direction, color);
        }
    }

    public void removeObserver(TrafficObserver observer) {
        observers.remove(observer);
    }
}
