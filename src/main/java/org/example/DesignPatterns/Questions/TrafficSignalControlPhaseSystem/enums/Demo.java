package org.example.DesignPatterns.Questions.TrafficSignalControlPhaseSystem.enums;

import java.util.List;

public class Demo {
    public static void main(String[] args) {

        // =========================
        // CREATE PHYSICAL LIGHTS
        // =========================

        TrafficLight north = new TrafficLight("L1", Direction.NORTH);
        TrafficLight south = new TrafficLight("L2", Direction.SOUTH);
        TrafficLight east  = new TrafficLight("L3", Direction.EAST);
        TrafficLight west  = new TrafficLight("L4", Direction.WEST);

        List<TrafficLight> allLights = List.of(north, south, east, west);

        // =========================
        // CREATE MOVEMENTS
        // =========================

        Movement northSouth =
                new Movement(MovementType.NORTH_TO_SOUTH, List.of(north, south));

        Movement eastWest =
                new Movement(MovementType.EAST_TO_WEST, List.of(east, west));

        // =========================
        // CREATE PHASE PLAN
        // =========================

        Phase nsPhase = new Phase(northSouth, 5000, 2000);
        Phase ewPhase = new Phase(eastWest, 5000, 2000);

        List<Phase> phases = List.of(nsPhase, ewPhase);

        // =========================
        // CONTROLLER
        // =========================

        IntersectionController controller =
                new IntersectionController(1, allLights, phases);

        Thread t = new Thread(controller);
        t.start();

        // =========================
        // MANUAL OVERRIDE DEMO
        // =========================

        try {
            Thread.sleep(15000);

            System.out.println("\n>>> TRAFFIC POLICE OVERRIDE NORTH-SOUTH");
            controller.enableManualOverride(northSouth);

            Thread.sleep(8000);

            System.out.println("\n>>> RESUME AUTO");
            controller.disableManualOverride();

            Thread.sleep(15000);

            controller.stopSystem();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
