package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.SpaceUser3D;

public class DiskTraverser3DTriangle extends SpaceTraverser implements SpaceUser3D, ConstantResolutionStepper {

    public DiskTraverser3DTriangle(int resolution){
        super(resolution);
    }
    public DiskTraverser3DTriangle(){
        super(Constants.PIXEL_RESOLUTION_LOW);
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(space().getXRange(), space().getYRange())/2;
        double radiusStep = getStep(Math.min(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin), this.resolution);
        double angleStep = getStep(Math.min(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin), this.resolution);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            double curAngleStep = angleStep*(1-radius/radiusMax);
            for(double angle = 0; angle < 2*Math.PI; angle += curAngleStep){
                handlePoint.accept(new double[]{radius*Math.cos(angle), (radius+radiusStep)*Math.cos(angle), (radius+radiusStep)*Math.cos(angle+angleStep), radius*Math.sin(angle), (radius+radiusStep)*Math.sin(angle), (radius+radiusStep)*Math.sin(angle+angleStep)});
                handlePoint.accept(new double[]{radius*Math.cos(angle), (radius)*Math.cos(angle+angleStep), (radius+radiusStep)*Math.cos(angle+angleStep), radius*Math.sin(angle), (radius)*Math.sin(angle+angleStep), (radius+radiusStep)*Math.sin(angle+angleStep)});
            }
        }
    }
}
