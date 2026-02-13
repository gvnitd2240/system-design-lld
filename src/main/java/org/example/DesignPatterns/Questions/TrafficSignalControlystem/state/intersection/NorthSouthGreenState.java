package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.intersection;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.IntersectionController;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.enums.Direction;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;

public class NorthSouthGreenState implements IntersectionState{
    @Override
    public void handle(IntersectionController context) throws InterruptedException {
        System.out.printf("\n--- INTERSECTION %d: Cycle Start -> North-South GREEN ---\n", context.getId());

        context.getLight(Direction.NORTH).startGreen();
        context.getLight(Direction.SOUTH).startGreen();

        context.getLight(Direction.EAST).setColor(LightColor.RED);
        context.getLight(Direction.WEST).setColor(LightColor.RED);

        Thread.sleep(context.getGreenDuration());

        context.getLight(Direction.NORTH).transition();
        context.getLight(Direction.SOUTH).transition();

        Thread.sleep(context.getYellowDuration());

        context.getLight(Direction.NORTH).transition();
        context.getLight(Direction.SOUTH).transition();


        context.setState(new EastWestGreenState());


    }
}
