package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class RectangleTraverser3D extends SpaceTraverser<Euclidean3D>{

    public static final double DEFAULT_PIXEL_RESOLUTION = 100;

    public RectangleTraverser3D(Euclidean3D space){
        super(space, DEFAULT_PIXEL_RESOLUTION);
        this.stepper = new ConstantResolutionTraverser();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(space.xAxisMax-space.xAxisMin, primaryPixelResolution);
        double yStep = stepper.getStep(space.yAxisMax-space.yAxisMin, secondaryPixelResolution);
        for(double x = space.xAxisMin; x <= space.xAxisMax; x += xStep){
            for(double y = space.yAxisMin; y <= space.yAxisMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
