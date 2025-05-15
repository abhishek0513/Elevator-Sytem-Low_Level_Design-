package org.example.SchedulingAlgoStrategyPackage.ConcreteStrategies;

import org.example.CommandPatternPackage.ConcreteClasses.ElevatorRequest;
import org.example.CommonEnum.Directions;
import org.example.SchedulingAlgoStrategyPackage.SchedulingStrategy;
import org.example.UtilityClasses.Elevator;

import java.util.Queue;

public class FCFSScheduling implements SchedulingStrategy{
    @Override
    public int getNextStop(Elevator elevator) {
        Directions directions = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest>requests = elevator.getRequestsQueue();

        if(requests.isEmpty())
            return currentFloor;

        int nextFloorStop = requests.poll().getFloor();

        if(nextFloorStop == currentFloor)
            return currentFloor;
        if(directions == Directions.IDLE){
            elevator.setDirection(
                    nextFloorStop > currentFloor ? Directions.UP : Directions.DOWN
            );
        }
        else if(directions == Directions.UP && nextFloorStop < currentFloor){
            elevator.setDirection(Directions.DOWN);
        }
        else if(directions == Directions.DOWN && nextFloorStop > currentFloor){
            elevator.setDirection(Directions.UP);
        }


        return nextFloorStop;
    }
}
