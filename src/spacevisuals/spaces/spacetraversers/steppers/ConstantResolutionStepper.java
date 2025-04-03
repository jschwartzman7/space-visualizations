package spacevisuals.spaces.spacetraversers.steppers;

public interface ConstantResolutionStepper extends Stepper{
    
    @Override
    public default double getStep(double axisRange, double pixelResolution){
        return axisRange/pixelResolution;
    }
}
