package org.example.DesignPatterns.Questions.TrafficSignalControlPhaseSystem.enums;
import java.util.List;

public class IntersectionController implements Runnable {

    private final int id;
    private final List<TrafficLight> allLights;
    private final List<Phase> phases;

    private volatile boolean running = false;
    private volatile boolean manualOverride = false;
    private Movement manualMovement;

    private int currentIndex = 0;

    public IntersectionController(
            int id,
            List<TrafficLight> allLights,
            List<Phase> phases
    ) {
        this.id = id;
        this.allLights = allLights;
        this.phases = phases;
    }

    // =========================
    // MAIN LOOP
    // =========================

    @Override
    public void run() {

        running = true;
        System.out.println("Intersection " + id + " started.");

        while (running) {

            try {

                if (manualOverride) {
                    executeManual();
                } else {
                    executeAuto();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        allRed();
        System.out.println("Intersection stopped safely.");
    }

    // =========================
    // AUTO MODE
    // =========================

    private void executeAuto() throws InterruptedException {

        Phase phase = phases.get(currentIndex);
        phase.execute();

        currentIndex = (currentIndex + 1) % phases.size();
    }

    // =========================
    // MANUAL MODE
    // =========================

    private void executeManual() throws InterruptedException {

        System.out.println("\n!!! MANUAL OVERRIDE ACTIVE !!!");

        allRed();
        Thread.sleep(1000);

        manualMovement.activateGreen();

        while (manualOverride && running) {
            Thread.sleep(500);
        }

        manualMovement.activateRed();
    }

    // =========================
    // CONTROL API
    // =========================

    public void stopSystem() {
        running = false;
    }

    public void enableManualOverride(Movement movement) {
        this.manualMovement = movement;
        this.manualOverride = true;
    }

    public void disableManualOverride() {
        this.manualOverride = false;
    }

    private void allRed() {
        allLights.forEach(l -> l.setColor(LightColor.RED));
    }
}
