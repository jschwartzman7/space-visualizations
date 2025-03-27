package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class RectangleTraverser3D extends SpaceTraverser<Euclidean3D>{

    public RectangleTraverser3D(Euclidean3D space, Stepper stepper){
        super(space, stepper);
    }
    public RectangleTraverser3D(Euclidean3D space, Stepper stepper, double defaultResolution){
        super(space, stepper, defaultResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(getSpace().xAxisMax-getSpace().xAxisMin, primaryResolution);
        double yStep = stepper.getStep(getSpace().yAxisMax-getSpace().yAxisMin, secondaryResolution);
        for(double x = getSpace().xAxisMin; x <= getSpace().xAxisMax; x += xStep){
            for(double y = getSpace().yAxisMin; y <= getSpace().yAxisMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
