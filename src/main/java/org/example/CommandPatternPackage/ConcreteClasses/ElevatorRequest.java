package org.example.CommandPatternPackage.ConcreteClasses;

import org.example.CommandPatternPackage.ElevatorCommand;
import org.example.CommonEnum.Directions;
import org.example.UtilityClasses.ElevatorController;

public class ElevatorRequest implements ElevatorCommand {
    private int elevatorId;
    private int floor;
    private Directions requestDirection;
    private ElevatorController controller;
    private  boolean isInternalRequest;


    public ElevatorRequest(int elevatorId, int floor,  boolean isInternalRequest, Directions requestDirection) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.requestDirection = requestDirection;
        this.isInternalRequest = isInternalRequest;
        this.controller = new ElevatorController();
    }

    @Override
    public void execute() {
        if(isInternalRequest){
            controller.requestFloor(elevatorId, floor);
        }
        else {
            controller.requestElevator(elevatorId, floor, requestDirection);
        }
    }

    public int getFloor() {
        return floor;
    }

    public Directions getRequestDirection() {
        return requestDirection;
    }

    public boolean checkIsInternalRequest() {
        return isInternalRequest;
    }
}
