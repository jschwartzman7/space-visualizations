package spacevisuals.spaces.spacetraversers;

import java.awt.Color;
import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.*;

public class AxisTraverser extends SpaceTraverser<Euclidean2D>{

    public AxisTraverser(Euclidean2D space, Stepper stepper){
        super(space, stepper);
    }
    public AxisTraverser(Euclidean2D space, Stepper stepper, double defaultPixelResolution){
        super(space, stepper, defaultPixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = stepper.getStep(getSpace().xClipMax-getSpace().xClipMin, primaryResolution);
        for(double x = getSpace().xClipMin; x <= getSpace().xClipMax; x += step){
                handlePoint.accept(new double[]{x});
        }
    }
}
