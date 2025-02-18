package spacevisuals.spaces.spacetraversers;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;
import spacevisuals.spaces.spacetraversers.steppers.*;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class ClippingDiskTraverser extends EuclideanSpaceTraverser<Euclidean2D>{
    
    public static final double DEFAULT_PIXEL_RESOLUTION = 100;
    public double radiusMin = 0.2;
    public double radiusMax = 3;

    public ClippingDiskTraverser(Euclidean2D space){
        super(space, DEFAULT_PIXEL_RESOLUTION);
        this.stepper = new ConstantResolutionTraverser();
    }
    public ClippingDiskTraverser(Euclidean2D space, double defaultPixelResolution){
        super(space, defaultPixelResolution);
        this.stepper = new ConstantResolutionTraverser();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusStep = stepper.getStep(Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin), primaryPixelResolution);
        double angleStep = stepper.getStep(Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin), secondaryPixelResolution);
        for(double radius = radiusMin; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
