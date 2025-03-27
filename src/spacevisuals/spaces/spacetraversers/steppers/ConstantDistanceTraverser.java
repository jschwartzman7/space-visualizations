package spacevisuals.spaces.spacetraversers.steppers;

import spacevisuals.Constants;

public class ConstantDistanceTraverser implements Stepper{
    
    public final double DEFAULT_DISTANCE = Constants.DISTANCE_STEP;
    public double distance;

    public ConstantDistanceTraverser(){
        this.distance = DEFAULT_DISTANCE;
    }
    public ConstantDistanceTraverser(double distance){
        this.distance = distance;
    }

    public double getStep(){
        return distance;
    }
    @Override
    public double getStep(double axisRange, double pixelResolution){
        return distance;
    }
}