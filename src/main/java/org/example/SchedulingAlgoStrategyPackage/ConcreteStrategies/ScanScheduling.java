package org.example.SchedulingAlgoStrategyPackage.ConcreteStrategies;

import org.example.CommandPatternPackage.ConcreteClasses.ElevatorRequest;
import org.example.CommonEnum.Directions;
import org.example.SchedulingAlgoStrategyPackage.SchedulingStrategy;
import org.example.UtilityClasses.Elevator;

import java.util.PriorityQueue;
import java.util.Queue;

public class ScanScheduling implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        Directions directions = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest>requests = elevator.getRequestsQueue();
        if(requests.isEmpty())
            return currentFloor;

        //Min Heap
        PriorityQueue<ElevatorRequest>upQueue = new PriorityQueue<>();
        PriorityQueue<ElevatorRequest>downQueue = new PriorityQueue<>(
                (a,b) -> b.getFloor() - a.getFloor()
        ); //Max-Heap


        while(!requests.isEmpty()){
            ElevatorRequest request = requests.poll();
            int floor = request.getFloor();
            if(floor > currentFloor){
                upQueue.add(request);
            }
            else{
                downQueue.add(request);
            }
        }
        if(directions == Directions.IDLE){
            int nearestUpwardRequest =
                    upQueue.isEmpty() ? -1 : upQueue.peek().getFloor();
            int nearestDownwardRequest =
                    downQueue.isEmpty() ? -1: downQueue.peek().getFloor();
            if(nearestUpwardRequest == -1){
                elevator.setDirection(Directions.DOWN);
                return downQueue.poll().getFloor();
            }
            else if(nearestDownwardRequest == -1){
                elevator.setDirection(Directions.UP);
                return upQueue.poll().getFloor();
            }
            else{
                if(Math.abs(nearestUpwardRequest - currentFloor)
                    < Math.abs(nearestDownwardRequest - currentFloor)){
                    elevator.setDirection(Directions.UP);
                    return upQueue.poll().getFloor();
                }
                else{
                    elevator.setDirection(Directions.DOWN);
                    return downQueue.poll().getFloor();
                }
            }
        }


        if(directions == Directions.UP){
            return !upQueue.isEmpty() ? upQueue.poll().getFloor()
                    :switchDirection(elevator,downQueue);
        }else{
            return !downQueue.isEmpty() ? downQueue.poll().getFloor()
                    :switchDirection(elevator,upQueue);
        }
    }

    private int switchDirection(Elevator elevator, PriorityQueue<ElevatorRequest> requestQueue) {
        elevator.setDirection(elevator.getDirection() == Directions.UP ?
                Directions.DOWN : Directions.UP);
        return requestQueue.isEmpty()? elevator.getCurrentFloor()
                :requestQueue.poll().getFloor();
    }
}
