package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.SpaceUser3D;

public class RectangleTraverser3DTriangle extends SpaceTraverser implements SpaceUser3D, ConstantResolutionStepper{

    public RectangleTraverser3DTriangle(int resolution){
        super(resolution);
    }
    public RectangleTraverser3DTriangle(){
        super(Constants.PIXEL_RESOLUTION_LOW);
    }

    // pass triangle coordinates to handlePoint
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xAxisMax-space().xAxisMin, this.resolution);
        double yStep = getStep(space().yAxisMax-space().yAxisMin, this.resolution);
        for(double x = space().xAxisMin; x <= space().xAxisMax; x += xStep){
            for(double y = space().yAxisMin; y <= space().yAxisMax; y += yStep){
                    handlePoint.accept(new double[]{x, x+xStep, x, y, y, y+yStep});
                    handlePoint.accept(new double[]{x+xStep, x, x+xStep, y+yStep, y+yStep, y});
            }
        }
    }
}
