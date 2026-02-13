package org.example.DesignPatterns.Questions.TrafficSignalControlystem;

import java.util.concurrent.TimeUnit;

public class TrafficDemo {
    public static void main(String[] args) {
        TrafficController controller = TrafficController.getInstance();

        controller.addIntersection(1, 500, 200);
        controller.addIntersection(2, 700, 150);

        controller.startSystem();

        // 4. Let the simulation run for a while (e.g., 5 seconds)
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 5. Stop the system gracefully
        controller.stopSystem();

    }
}