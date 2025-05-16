package org.example.UtilityClasses;

import org.example.CommandPatternPackage.ConcreteClasses.ElevatorRequest;
import org.example.CommonEnum.Directions;
import org.example.CommonEnum.ElevatorState;
import org.example.ObserverPatternPackage.ElevatorObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Elevator {
    private int id;
    private int currentFloor;
    private Directions directions;
    private ElevatorState elevatorState;
    private List<ElevatorObserver> observers;
    private Queue<ElevatorRequest> requests;


    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.directions = Directions.IDLE;
        this.elevatorState = ElevatorState.IDLE;
        this.observers = new ArrayList<>();
        this.requests = new LinkedList<>();
    }


    public void addObserver(ElevatorObserver elevatorObserver) {
        observers.add(elevatorObserver);
    }

    public void removeObserver(ElevatorObserver elevatorObserver) {
        observers.add(elevatorObserver);
    }

    private void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorStateChange(this, state);
        }
    }

    private void notifyFloorChange(int floor) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorFloorChange(this, floor);
        }
    }

    public void addRequest(ElevatorRequest elevatorRequest) {
        // Avoid duplicate requests
        if (!requests.contains(elevatorRequest)) {
            requests.add(elevatorRequest);
        }

        int requestedFloor = elevatorRequest.getFloor();
        // If elevator is idle, determine direction and start moving
        if (elevatorState == ElevatorState.IDLE && !requests.isEmpty()) {
            if (requestedFloor > currentFloor) {
                directions = Directions.UP;
            } else if (requestedFloor < currentFloor) {
                directions = Directions.DOWN;
            }
            setState(ElevatorState.MOVING);
        }
    }

    public void setState(ElevatorState newState) {
        this.elevatorState = newState;
        notifyStateChange(newState);
    }

    // Set the direction of the elevator
    public void setDirection(Directions newDirection) {
        this.directions = newDirection;
    }

    public int getId() {
        return id;
    }

    // Get the elevator's current floor
    public int getCurrentFloor() {
        return currentFloor;
    }

    // Get the elevator's current direction
    public Directions getDirection() {
        return directions;
    }

    // Get the elevator's current state
    public ElevatorState getState() {
        return elevatorState;
    }

    // Get a copy of the current requests queue to prevent external modification
    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }

    // Get a list of all destination floors for display purposes
    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }

}
