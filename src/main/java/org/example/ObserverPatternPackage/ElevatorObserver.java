package org.example.ObserverPatternPackage;

import org.example.CommonEnum.ElevatorState;
import org.example.UtilityClasses.Elevator;

public interface ElevatorObserver {
    void onElevatorStateChange(Elevator elevator, ElevatorState state);
    void onElevatorFloorChange(Elevator elevator, int floor);
}
