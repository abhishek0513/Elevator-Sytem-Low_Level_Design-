package org.example.SchedulingAlgoStrategyPackage.ConcreteStrategies;

import org.example.CommandPatternPackage.ConcreteClasses.ElevatorRequest;
import org.example.CommonEnum.Directions;
import org.example.SchedulingAlgoStrategyPackage.SchedulingStrategy;
import org.example.UtilityClasses.Elevator;

import java.util.Queue;

public class LookScheduling implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        int currentFloor =elevator.getCurrentFloor();
        Queue<ElevatorRequest> requestQueue = elevator.getRequestsQueue();
        if(requestQueue == null || requestQueue.isEmpty()){
            return currentFloor;
        }
        ElevatorRequest primaryRequest = requestQueue.peek();
        int primaryFloor = primaryRequest.getFloor();
        Directions  travelDirection;
        if(primaryFloor > currentFloor){
            travelDirection = Directions.UP;
        }
        else if(primaryFloor < currentFloor){
            travelDirection = Directions.DOWN;
        }
        else{
            return currentFloor;
        }

        Integer candidate = null;
        for(ElevatorRequest req: requestQueue){
            int reqFloor = req.getFloor();
            if(travelDirection==Directions.UP && reqFloor > currentFloor
                    && reqFloor <= primaryFloor){
                if (req.checkIsInternalRequest()
                        || (!req.checkIsInternalRequest() && req.getRequestDirection() == Directions.UP)) {
                    // Choose the candidate that is closest to the current floor (i.e. the smallest floor
                    // greater than currentFloor).
                    if (candidate == null || reqFloor < candidate) {
                        candidate = reqFloor;
                    }
                }
            }
            else if (travelDirection == Directions.DOWN && reqFloor < currentFloor
                    && reqFloor >= primaryFloor) {
                // For downward movement, consider the request if internal or if external with direction
                // DOWN.
                if (req.checkIsInternalRequest()
                        || (!req.checkIsInternalRequest() && req.getRequestDirection() == Directions.DOWN)) {
                    // For a downward journey, we choose the candidate that is closest to the current floor
                    // (i.e. the largest floor less than currentFloor).
                    if (candidate == null || reqFloor > candidate) {
                        candidate = reqFloor;
                    }
                }
            }
        }
        // If a candidate was found in the path, return that as the next stop;
        // otherwise, fall back to the primary target.
        return (candidate != null) ? candidate : primaryFloor;
        }
}
