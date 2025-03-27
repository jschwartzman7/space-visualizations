package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import spacevisuals.spaces.spacetraversers.steppers.Stepper;

public class AxisTraverserLine extends SpaceTraverser<Euclidean2D>{
    
    public AxisTraverserLine(Euclidean2D space, Stepper stepper){
        super(space, stepper);
    }
    public AxisTraverserLine(Euclidean2D space, Stepper stepper, double defaultPixelResolution){
        super(space, stepper, defaultPixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = stepper.getStep(getSpace().xClipMax-getSpace().xClipMin, primaryResolution);
        for(double x = getSpace().xClipMin; x <= getSpace().xClipMax; x += step){
            handlePoint.accept(new double[]{x, x+step});
        }
    }
}
