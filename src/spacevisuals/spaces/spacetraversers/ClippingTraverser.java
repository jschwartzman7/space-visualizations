package spacevisuals.spaces.spacetraversers;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class ClippingTraverser extends EuclideanSpaceTraverser<Euclidean2D>{
    
    public ClippingTraverser(Euclidean2D space){
        super(space);
    }
    public ClippingTraverser(Euclidean2D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xClipMax-space.xClipMin)/primaryPixelResolution;
        double yStep = (space.yClipMax-space.yClipMin)/secondaryPixelResolution;
        for(double x = space.xClipMin; x <= space.xClipMax; x += xStep){
            for(double y = space.yClipMin; y <= space.yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] point, Color color){
        double halfWidth = (space.xClipMax-space.xClipMin)/(2*primaryPixelResolution);
        double halfHeight = (space.yClipMax-space.yClipMin)/(2*secondaryPixelResolution);
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
