package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.*;

public class PrismTraverser3D extends SpaceTraverser<Euclidean3D>{

    public PrismTraverser3D(Euclidean3D space, Stepper stepper){
        super(space, stepper);
    }
    public PrismTraverser3D(Euclidean3D space, Stepper stepper, double defaultPixelResolution){
        super(space, stepper, defaultPixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(getSpace().xAxisMax-getSpace().xAxisMin, primaryResolution);
        double yStep = stepper.getStep(getSpace().yAxisMax-getSpace().yAxisMin, primaryResolution);
        double zStep = stepper.getStep(getSpace().zAxisMax-getSpace().zAxisMin, secondaryResolution);
        for(double x = getSpace().xAxisMin; x <= getSpace().xAxisMax; x += xStep){
            for(double y = getSpace().yAxisMin; y <= getSpace().yAxisMax; y += yStep){
                for(double z = getSpace().zAxisMin; z <= getSpace().zAxisMax; z += zStep){
                    handlePoint.accept(new double[]{x, y, z});
                }
            }
        }
    }
}
