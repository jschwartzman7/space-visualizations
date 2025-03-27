package spacevisuals.spaces.spacetraversers;

import java.awt.Color;
import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class ClippingTraverser extends SpaceTraverser<Euclidean2D>{

    public ClippingTraverser(Euclidean2D space, Stepper stepper){
        super(space, stepper);
    }
    public ClippingTraverser(Euclidean2D space, Stepper stepper, double defaultPixelResolution){
        super(space, stepper, defaultPixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = stepper.getStep(getSpace().xClipMax-getSpace().xClipMin, primaryResolution);
        double yStep = stepper.getStep(getSpace().yClipMax-getSpace().yClipMin, secondaryResolution);
        for(double x = getSpace().xClipMin; x <= getSpace().xClipMax; x += xStep){
            for(double y = getSpace().yClipMin; y <= getSpace().yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] point, Color color){
        double halfWidth = stepper.getStep(getSpace().xClipMax-getSpace().xClipMin, primaryResolution)/2;
        double halfHeight = stepper.getStep(getSpace().yClipMax-getSpace().yClipMin, secondaryResolution)/2;
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
