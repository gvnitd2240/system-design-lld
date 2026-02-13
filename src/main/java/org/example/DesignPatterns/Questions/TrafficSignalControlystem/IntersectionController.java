package org.example.DesignPatterns.Questions.TrafficSignalControlystem;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.TrafficLight;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.enums.Direction;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.observer.TrafficObserver;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.intersection.IntersectionState;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.intersection.NorthSouthGreenState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionController implements Runnable {
    private final int id;
    private final Map<Direction, TrafficLight> trafficLights;
    private IntersectionState currentState;
    private final long greenDuration;
    private final long yellowDuration;
    private volatile boolean running = true;

    public IntersectionController(int id, Map<Direction, TrafficLight> trafficLights, long greenDuration, long yellowDuration) {
        this.id = id;
        this.trafficLights = trafficLights;
        this.currentState = new NorthSouthGreenState();
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
    }

    public int getId() {
        return id;
    }

    public Map<Direction, TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    public IntersectionState getCurrentState() {
        return currentState;
    }

    public long getGreenDuration() {
        return greenDuration;
    }

    public long getYellowDuration() {
        return yellowDuration;
    }

    public boolean isRunning() {
        return running;
    }

    public void setState(IntersectionState state) {
        this.currentState = state;
    }

    public void start() {
        new Thread(this).start();
    }

    public void stop() {
        this.running = false;
    }

    public TrafficLight getLight(Direction dir){
        return trafficLights.get(dir);
    }

    @Override
    public void run() {
        while (running) {
            try {
                currentState.handle(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Intersection " + id + " was interrupted.");
                running = false;
            }
        }
    }



    public static class Builder{
        private final int id;
        private long greenDuration = 5000; // default 5s
        private long yellowDuration = 2000; // default 2s
        private final List<TrafficObserver> observers = new ArrayList<>();

        public Builder(int id){
            this.id = id;
        }

        public Builder withDurations(long green, long yellow){
            this.greenDuration = green;
            this.yellowDuration = yellow;
            return this;
        }


        public Builder addObserver(TrafficObserver observer) {
            this.observers.add(observer);
            return this;
        }

        public IntersectionController build() {
            Map<Direction, TrafficLight> lights = new HashMap<>();
            for(Direction direction: Direction.values()){
                TrafficLight light = new TrafficLight(id, direction);
                // Attach all registered observers to each light
                observers.forEach(light::addObserver);
                lights.put(direction, light);
            }

            return new IntersectionController(id, lights, greenDuration, yellowDuration);
        }

    }
}
