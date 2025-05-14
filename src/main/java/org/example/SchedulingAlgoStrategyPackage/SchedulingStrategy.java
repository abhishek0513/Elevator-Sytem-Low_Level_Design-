package org.example.SchedulingAlgoStrategyPackage;

import org.example.UtilityClasses.Elevator;
public interface SchedulingStrategy{
    int getNextStop(Elevator elevator);
}
