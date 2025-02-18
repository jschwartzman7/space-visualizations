package spacevisuals.spaces.spacetraversers.steppers;

import spacevisuals.spaces.AbstractSpace;

public class ConstantDistanceTraverser implements Stepper{
    
    public final double DEFAULT_DISTANCE = 0.5;
    public double distance;

    public ConstantDistanceTraverser(){
        this.distance = DEFAULT_DISTANCE;
    }
    public ConstantDistanceTraverser(double distance){
        this.distance = distance;
    }

    @Override
    public double getStep(double axisRange, double pixelResolution){
        return distance;
    }
}