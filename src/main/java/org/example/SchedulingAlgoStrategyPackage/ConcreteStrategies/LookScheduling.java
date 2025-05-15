package org.example.SchedulingAlgoStrategyPackage.ConcreteStrategies;

import org.example.SchedulingAlgoStrategyPackage.SchedulingStrategy;
import org.example.UtilityClasses.Elevator;

public class LookScheduling implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        return 0;
    }
}
