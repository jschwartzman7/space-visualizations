package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class PrismTraverser3D extends SpaceTraverser implements SpaceUser3D, ConstantResolutionStepper {

    public PrismTraverser3D(int resolution){
        super(resolution);
    }
    public PrismTraverser3D(){
        super(Constants.PIXEL_RESOLUTION_LOW);
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xAxisMax-space().xAxisMin, this.resolution);
        double yStep = getStep(space().yAxisMax-space().yAxisMin, this.resolution);
        double zStep = getStep(space().zAxisMax-space().zAxisMin, this.resolution);
        for(double x = space().xAxisMin; x <= space().xAxisMax; x += xStep){
            for(double y = space().yAxisMin; y <= space().yAxisMax; y += yStep){
                for(double z = space().zAxisMin; z <= space().zAxisMax; z += zStep){
                    handlePoint.accept(new double[]{x, y, z});
                }
            }
        }
    }
}
