package org.example.DesignPatterns.Questions.TrafficSignalControlPhaseSystem.enums;

public class Phase {
    private final Movement movement;
    private final int greenDurationMs;
    private final int yellowDurationMs;

    public Phase(Movement movement, int greenDurationMs, int yellowDurationMs) {
        this.movement = movement;
        this.greenDurationMs = greenDurationMs;
        this.yellowDurationMs = yellowDurationMs;
    }

    public void execute() throws InterruptedException {

        System.out.println("\n--- MOVEMENT " + movement.getType() + " GREEN ---");
        movement.activateGreen();
        Thread.sleep(greenDurationMs);

        System.out.println("--- MOVEMENT " + movement.getType() + " YELLOW ---");
        movement.activateYellow();
        Thread.sleep(yellowDurationMs);

        System.out.println("--- MOVEMENT " + movement.getType() + " RED ---");
        movement.activateRed();
    }
}
