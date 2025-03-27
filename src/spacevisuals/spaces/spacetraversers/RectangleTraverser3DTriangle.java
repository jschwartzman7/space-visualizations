package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.Constants;
import spacevisuals.spaces.Euclidean3D;

public class RectangleTraverser3DTriangle extends SpaceTraverser<Euclidean3D>{

    public static final double DEFAULT_PIXEL_RESOLUTION = Constants.PIXEL_RESOLUTION_LOW;

    public RectangleTraverser3DTriangle(Euclidean3D space, Stepper stepper){
        super(space, stepper, DEFAULT_PIXEL_RESOLUTION);
    }
    public RectangleTraverser3DTriangle(Euclidean3D space, Stepper stepper, double defaultResolution){
        super(space, stepper, defaultResolution);
    }

    // pass triangle coordinates to handlePoint
    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(getSpace().xAxisMax-getSpace().xAxisMin, primaryResolution);
        double yStep = stepper.getStep(getSpace().yAxisMax-getSpace().yAxisMin, secondaryResolution);
        for(double x = getSpace().xAxisMin; x <= getSpace().xAxisMax; x += xStep){
            for(double y = getSpace().yAxisMin; y <= getSpace().yAxisMax; y += yStep){
                    handlePoint.accept(new double[]{x, x+xStep, x, y, y, y+yStep});
                    handlePoint.accept(new double[]{x+xStep, x, x+xStep, y+yStep, y+yStep, y});
            }
        }
    }
}
