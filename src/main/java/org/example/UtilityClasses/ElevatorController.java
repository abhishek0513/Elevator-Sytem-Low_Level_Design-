package org.example.UtilityClasses;

import org.example.CommandPatternPackage.ConcreteClasses.ElevatorRequest;
import org.example.CommonEnum.Directions;
import org.example.SchedulingAlgoStrategyPackage.ConcreteStrategies.ScanScheduling;
import org.example.SchedulingAlgoStrategyPackage.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;
    private List<Floor>floors;
    private SchedulingStrategy strategy;
    private int currentElevtorId;

    public ElevatorController() {
    }

    public ElevatorController(int numberofElevators, int numberofFloors){
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.strategy = new ScanScheduling();
        for(int i = 1; i <= numberofElevators; i++){
            elevators.add(new Elevator(i));
        }
        for(int i = 1; i < numberofFloors; i++){
            floors.add(new Floor(i));
        }
    }
    public void requestElevator(int elevatorId, int floorNumber, Directions direction) {
        System.out.println(
                "External request: Floor " + floorNumber + ", Direction " + direction);
        // Find the elevator by its ID
        Elevator selectedElevator = getElevatorById(elevatorId);
        if (selectedElevator != null) {
            // Add the request to the selected elevator
            selectedElevator.addRequest(
                    new ElevatorRequest(elevatorId, floorNumber, false, direction));
            System.out.println("Assigned elevator " + selectedElevator.getId()
                    + " to floor " + floorNumber);
        } else {
            // If no suitable elevator is found
            System.out.println("No elevator available for floor " + floorNumber);
        }
    }

    // Handle internal elevator requests to a specific floor
    public void requestFloor(int elevatorId, int floorNumber) {
        // Find the elevator by its ID
        Elevator elevator = getElevatorById(elevatorId);
        System.out.println("Internal request: Elevator " + elevator.getId()
                + " to floor " + floorNumber);
        // Determine the direction of the request
        Directions direction = floorNumber > elevator.getCurrentFloor()
                ? Directions.UP
                : Directions.DOWN;
        // Add the request to the elevator
        elevator.addRequest(
                new ElevatorRequest(elevatorId, floorNumber, true, direction));
    }

    // Find an elevator by its ID
    private Elevator getElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId)
                return elevator;
        }
        return null; // Return null if no matching elevator is found
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    // Get the list of all floors
    public List<Floor> getFloors() {
        return floors;
    }

    // Set the ID of the current elevator
    public void setCurrentElevator(int elevatorId) {
        this.currentElevtorId = elevatorId;
    }

    public void setStrategy(SchedulingStrategy strategy){
        this.strategy = strategy;
    }

}
