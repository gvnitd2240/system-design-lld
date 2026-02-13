package org.example.DesignPatterns.Questions.TrafficSignalControlystem.state.intersection;

import org.example.DesignPatterns.Questions.TrafficSignalControlystem.IntersectionController;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.entities.LightColor;
import org.example.DesignPatterns.Questions.TrafficSignalControlystem.enums.Direction;

public class EastWestGreenState implements IntersectionState{
    @Override
    public void handle(IntersectionController context) throws InterruptedException {

        context.getLight(Direction.EAST).startGreen();
        context.getLight(Direction.WEST).startGreen();

        context.getLight(Direction.NORTH).setColor(LightColor.RED);
        context.getLight(Direction.SOUTH).setColor(LightColor.RED);

        Thread.sleep(context.getGreenDuration());

        context.getLight(Direction.EAST).transition();
        context.getLight(Direction.WEST).transition();


        context.getLight(Direction.EAST).setColor(LightColor.YELLOW);
        context.getLight(Direction.WEST).setColor(LightColor.YELLOW);


        Thread.sleep(context.getYellowDuration());

        context.getLight(Direction.EAST).transition();
        context.getLight(Direction.WEST).transition();

        context.getLight(Direction.EAST).setColor(LightColor.RED);
        context.getLight(Direction.WEST).setColor(LightColor.RED);

        context.setState(new NorthSouthGreenState());
    }
}
