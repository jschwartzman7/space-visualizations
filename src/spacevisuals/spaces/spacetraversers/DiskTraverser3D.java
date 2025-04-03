package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.SpaceUser3D;

public class DiskTraverser3D implements SpaceUser3D, SpaceTraverser, ConstantResolutionStepper {


    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(space().getXRange(), space().getYRange())/2;
        double radiusStep = getStep(Math.min(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin), Constants.PIXEL_RESOLUTION_MEDIUM);
        double angleStep = getStep(Math.min(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin), Constants.PIXEL_RESOLUTION_MEDIUM);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
