package org.example.ObserverPatternPackage;

import org.example.CommonEnum.ElevatorState;
import org.example.UtilityClasses.Elevator;

public class ElevatorDisplay implements ElevatorObserver{

    @Override
    public void onElevatorStateChange(Elevator elevator, ElevatorState state) {
        System.out.println("Elevator " + elevator.getId()  + " state changed to " + state);
    }

    @Override
    public void onElevatorFloorChange(Elevator elevator, int floor) {
        System.out.println("Elevator " + elevator.getId() + " moved to floor " + floor);
    }
}
