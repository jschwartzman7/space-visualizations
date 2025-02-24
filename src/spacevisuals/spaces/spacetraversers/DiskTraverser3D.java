package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class DiskTraverser3D extends SpaceTraverser<Euclidean3D>{

    public static final double DEFAULT_PIXEL_RESOLUTION = 100;
    public double radiusMin = 0.2;
    public double radiusMax = 3;

    public DiskTraverser3D(Euclidean3D space){
        super(space, DEFAULT_PIXEL_RESOLUTION);
        this.stepper = new ConstantResolutionTraverser();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusStep = stepper.getStep(Math.min(space.xAxisMax-space.xAxisMin, space.yAxisMax-space.yAxisMin), primaryPixelResolution);
        double angleStep = stepper.getStep(Math.min(space.xAxisMax-space.xAxisMin, space.yAxisMax-space.yAxisMin), primaryPixelResolution);
        for(double radius = radiusMin; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
