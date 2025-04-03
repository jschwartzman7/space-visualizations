package spacevisuals.spaces.spacetraversers.steppers;

import spacevisuals.utils.Constants;

public interface ConstantDistanceStepper extends Stepper{
    
    double DISTANCE = Constants.DISTANCE_STEP;
    
    @Override
    public default double getStep(double axisRange, double pixelResolution){
        return DISTANCE;
    }
}