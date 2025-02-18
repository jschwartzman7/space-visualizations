package spacevisuals.spaces.spacetraversers.steppers;

import spacevisuals.spaces.AbstractSpace;

public class ConstantResolutionTraverser implements Stepper{
    
    public ConstantResolutionTraverser(){}

    @Override
    public double getStep(double axisRange, double pixelResolution){
        return axisRange/pixelResolution;
    }
}
