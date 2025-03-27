package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class DiskTraverser3DTriangle extends SpaceTraverser<Euclidean3D>{

    public DiskTraverser3DTriangle(Euclidean3D space, Stepper stepper){
        super(space, stepper);
    }
    public DiskTraverser3DTriangle(Euclidean3D space, Stepper stepper, double defaultResolution){
        super(space, stepper, defaultResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(getSpace().getXRange(), getSpace().getYRange())/2;
        double radiusStep = stepper.getStep(Math.min(getSpace().xAxisMax-getSpace().xAxisMin, getSpace().yAxisMax-getSpace().yAxisMin), primaryResolution);
        double angleStep = stepper.getStep(Math.min(getSpace().xAxisMax-getSpace().xAxisMin, getSpace().yAxisMax-getSpace().yAxisMin), primaryResolution);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            double curAngleStep = angleStep*(1-radius/radiusMax);
            for(double angle = 0; angle < 2*Math.PI; angle += curAngleStep){
                handlePoint.accept(new double[]{radius*Math.cos(angle), (radius+radiusStep)*Math.cos(angle), (radius+radiusStep)*Math.cos(angle+angleStep), radius*Math.sin(angle), (radius+radiusStep)*Math.sin(angle), (radius+radiusStep)*Math.sin(angle+angleStep)});
                handlePoint.accept(new double[]{radius*Math.cos(angle), (radius)*Math.cos(angle+angleStep), (radius+radiusStep)*Math.cos(angle+angleStep), radius*Math.sin(angle), (radius)*Math.sin(angle+angleStep), (radius+radiusStep)*Math.sin(angle+angleStep)});
            }
        }
    }
}
