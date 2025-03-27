package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class DiskTraverser3D extends SpaceTraverser<Euclidean3D>{

    public DiskTraverser3D(Euclidean3D space, Stepper stepper){
        super(space, stepper);
    }
    public DiskTraverser3D(Euclidean3D space, Stepper stepper, double defaultResolution){
        super(space, stepper, defaultResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(getSpace().getXRange(), getSpace().getYRange())/2;
        double radiusStep = stepper.getStep(Math.min(getSpace().xAxisMax-getSpace().xAxisMin, getSpace().yAxisMax-getSpace().yAxisMin), primaryResolution);
        double angleStep = stepper.getStep(Math.min(getSpace().xAxisMax-getSpace().xAxisMin, getSpace().yAxisMax-getSpace().yAxisMin), primaryResolution);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
