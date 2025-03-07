package spacevisuals.spaces.spacetraversers.steppers;

public class ConstantResolutionTraverser implements Stepper{
    
    public ConstantResolutionTraverser(){}

    @Override
    public double getStep(double axisRange, double pixelResolution){
        return axisRange/pixelResolution;
    }
}
