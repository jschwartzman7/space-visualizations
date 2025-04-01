package spacevisuals.spaces.spacetraversers.steppers;

public interface ConstantResolutionTraverser extends Stepper{
    
    @Override
    public default double getStep(double axisRange, double pixelResolution){
        return axisRange/pixelResolution;
    }
}
