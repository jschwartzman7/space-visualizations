package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser3D;

public class RectangleTraverser3DTriangle implements SpaceUser3D, SpaceTraverser, ConstantResolutionTraverser{

    public static final double DEFAULT_PIXEL_RESOLUTION = Constants.PIXEL_RESOLUTION_LOW;

    // pass triangle coordinates to handlePoint
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xAxisMax-space().xAxisMin, DEFAULT_PIXEL_RESOLUTION);
        double yStep = getStep(space().yAxisMax-space().yAxisMin, DEFAULT_PIXEL_RESOLUTION);
        for(double x = space().xAxisMin; x <= space().xAxisMax; x += xStep){
            for(double y = space().yAxisMin; y <= space().yAxisMax; y += yStep){
                    handlePoint.accept(new double[]{x, x+xStep, x, y, y, y+yStep});
                    handlePoint.accept(new double[]{x+xStep, x, x+xStep, y+yStep, y+yStep, y});
            }
        }
    }
}
