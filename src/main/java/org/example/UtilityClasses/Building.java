package org.example.UtilityClasses;

public class Building {
    private String name;
    private int numberofFloors;
    private ElevatorController elevatorController;

    public Building(String name, int numberofFloors, int numberofElevators) {
        this.name = name;
        this.numberofFloors = numberofFloors;
        this.elevatorController = new ElevatorController(numberofElevators, numberofFloors);
    }

    public String getName() {
        return name;
    }

    public int getNumberofFloors() {
        return numberofFloors;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }
}
