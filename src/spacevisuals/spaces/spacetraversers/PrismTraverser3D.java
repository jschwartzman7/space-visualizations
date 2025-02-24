package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.*;

public class PrismTraverser3D extends SpaceTraverser<Euclidean3D>{

    public static final double DEFAULT_PIXEL_RESOLUTION = 100;

    public PrismTraverser3D(Euclidean3D space){
        super(space, DEFAULT_PIXEL_RESOLUTION);
        this.stepper = new ConstantResolutionTraverser();
    }
    public PrismTraverser3D(Euclidean3D space, double defaultPixelResolution){
        super(space, defaultPixelResolution);
        this.stepper = new ConstantResolutionTraverser();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(space.xAxisMax-space.xAxisMin, primaryPixelResolution);
        double yStep = stepper.getStep(space.yAxisMax-space.yAxisMin, primaryPixelResolution);
        double zStep = stepper.getStep(space.zAxisMax-space.zAxisMin, secondaryPixelResolution);
        for(double x = space.xAxisMin; x <= space.xAxisMax; x += xStep){
            for(double y = space.yAxisMin; y <= space.yAxisMax; y += yStep){
                for(double z = space.zAxisMin; z <= space.zAxisMax; z += zStep){
                    handlePoint.accept(new double[]{x, y, z});
                }
            }
        }
    }
}
