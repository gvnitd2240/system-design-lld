package org.example.DesignPatterns.Questions.TrafficSignalControlystem;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.observer.CentralMonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TrafficController {
    private static TrafficController instance;
    private List<IntersectionController> intersectionControllers;
    private ExecutorService executorService;

    private TrafficController(){
        this.intersectionControllers = new ArrayList<>();
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public static TrafficController getInstance(){
        if(instance == null){
            synchronized (TrafficController.class){
                if(instance == null){
                    instance = new TrafficController();
                }
            }
        }

        return instance;
    }

    public void addIntersection(int intersectionId, int greenDuration, int yellowDuration){
        this.intersectionControllers.add(new IntersectionController.Builder(intersectionId).addObserver(new CentralMonitor()).withDurations(greenDuration, yellowDuration).build());
    }

    public void startSystem(){
        if (intersectionControllers.isEmpty()) {
            System.out.println("No intersections to manage. System not starting.");
            return;
        }

        System.out.println("--- Starting Traffic Control System ---");
        executorService = Executors.newFixedThreadPool(intersectionControllers.size());
        intersectionControllers.forEach(executorService::submit);
    }

    public void stopSystem() {
        System.out.println("\n--- Shutting Down Traffic Control System ---");
        intersectionControllers.forEach(IntersectionController::stop);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        System.out.println("All intersections stopped. System shut down.");
    }
}
