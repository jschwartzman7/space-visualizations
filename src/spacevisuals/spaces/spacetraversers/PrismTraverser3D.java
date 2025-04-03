package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class PrismTraverser3D implements SpaceUser3D, SpaceTraverser, ConstantResolutionStepper {

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xAxisMax-space().xAxisMin, Constants.PIXEL_RESOLUTION_LOW);
        double yStep = getStep(space().yAxisMax-space().yAxisMin, Constants.PIXEL_RESOLUTION_LOW);
        double zStep = getStep(space().zAxisMax-space().zAxisMin, Constants.PIXEL_RESOLUTION_LOW);
        for(double x = space().xAxisMin; x <= space().xAxisMax; x += xStep){
            for(double y = space().yAxisMin; y <= space().yAxisMax; y += yStep){
                for(double z = space().zAxisMin; z <= space().zAxisMax; z += zStep){
                    handlePoint.accept(new double[]{x, y, z});
                }
            }
        }
    }
}
