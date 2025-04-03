package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.SpaceUser3D;

public class RectangleTraverser3D implements SpaceUser3D, SpaceTraverser, ConstantResolutionStepper {

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xAxisMax-space().xAxisMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        double yStep = getStep(space().yAxisMax-space().yAxisMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        for(double x = space().xAxisMin; x <= space().xAxisMax; x += xStep){
            for(double y = space().yAxisMin; y <= space().yAxisMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
