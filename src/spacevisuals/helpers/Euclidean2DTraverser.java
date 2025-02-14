package spacevisuals.helpers;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class Euclidean2DTraverser extends EuclideanSpaceTraverser<Euclidean2D>{
    
    public Euclidean2DTraverser(Euclidean2D space){
        super(space);
    }
    public Euclidean2DTraverser(Euclidean2D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xClipMax-space.xClipMin)/primaryPixelResolution;
        double yStep = (space.yClipMax-space.yClipMin)/secondaryPixelResolution;
        double numericXMin = space.xClipMin;
        double numericXMax = space.xClipMax;
        double numericYMin = space.yClipMin;
        double numericYMax = space.yClipMax;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] numericPoint, Color color){
        double[] point = space.toViewScreenPoint(numericPoint);
        double halfWidth = (space.xClipMax-space.xClipMin)/(2*primaryPixelResolution);
        double halfHeight = (space.yClipMax-space.yClipMin)/(2*secondaryPixelResolution);
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
