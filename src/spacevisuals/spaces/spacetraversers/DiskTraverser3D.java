package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.SpaceUser3D;

public class DiskTraverser3D extends SpaceTraverser implements SpaceUser3D, ConstantResolutionStepper {

    public DiskTraverser3D(int resolution){
        super(resolution);
    }
    public DiskTraverser3D(){
        super(Constants.PIXEL_RESOLUTION_MEDIUM);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin)/2;
        double radiusStep = getStep(Math.min(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin), this.resolution);
        double angleStep = getStep(Math.PI*2, this.resolution);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
