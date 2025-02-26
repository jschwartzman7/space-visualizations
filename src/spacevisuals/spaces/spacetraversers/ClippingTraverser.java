package spacevisuals.spaces.spacetraversers;

import java.awt.Color;
import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class ClippingTraverser extends SpaceTraverser<Euclidean2D>{
    
    public static final double DEFAULT_PIXEL_RESOLUTION = 100;

    public ClippingTraverser(Euclidean2D space){
        super(space, DEFAULT_PIXEL_RESOLUTION);
        this.stepper = new ConstantResolutionTraverser();
    }
    public ClippingTraverser(Euclidean2D space, double defaultPixelResolution){
        super(space, defaultPixelResolution);
        this.stepper = new ConstantResolutionTraverser();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(space.xClipMax-space.xClipMin, primaryPixelResolution);
        double yStep = stepper.getStep(space.yClipMax-space.yClipMin, secondaryPixelResolution);
        for(double x = space.xClipMin; x <= space.xClipMax; x += xStep){
            for(double y = space.yClipMin; y <= space.yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] point, Color color){
        double halfWidth = stepper.getStep(space.xClipMax-space.xClipMin, primaryPixelResolution)/2;
        double halfHeight = stepper.getStep(space.yClipMax-space.yClipMin, secondaryPixelResolution)/2;
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
