package spacevisuals.spaces.helpers;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class Lattice2DHelper2D extends EuclideanSpaceTraverser<Euclidean2D>{
    
    public Lattice2DHelper2D(Euclidean2D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xMaxClip-space.xMinClip)/primaryPixelResolution;
        double yStep = (space.yMaxClip-space.yMinClip)/secondaryPixelResolution;
        double numericXMin = space.xMinClip;
        double numericXMax = space.xMaxClip;
        double numericYMin = space.yMinClip;
        double numericYMax = space.yMaxClip;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] numericPoint, Color color){
        double[] point = space.toDrawablePoint(numericPoint);
        double halfWidth = (space.xMaxClip-space.xMinClip)/(2*primaryPixelResolution);
        double halfHeight = (space.yMaxClip-space.yMinClip)/(2*secondaryPixelResolution);
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
