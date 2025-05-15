package org.example.UtilityClasses;

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
