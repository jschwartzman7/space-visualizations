package spacevisuals.spaces;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class Lattice2DHelper<ConcreteSpace extends AbstractSpace> extends EuclideanSpaceTraverser<ConcreteSpace>{
    
    public Lattice2DHelper(ConcreteSpace space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<Double[]> handlePoint){
        double xStep = (space.X_MAX-space.X_MIN)*space.primaryDistortion/primaryPixelResolution;
        double yStep = (space.Y_MAX-space.Y_MIN)*space.secondaryDistortion/secondaryPixelResolution;
        double numericXMin = space.X_MIN*space.primaryDistortion;
        double numericXMax = space.X_MAX*space.primaryDistortion;
        double numericYMin = space.Y_MIN*space.secondaryDistortion;
        double numericYMax = space.Y_MAX*space.secondaryDistortion;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                handlePoint.accept(new Double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] numericPoint, Color color){
        double[] point = space.toDrawablePoint(numericPoint);
        double halfWidth = (space.X_MAX-space.X_MIN)/(2*primaryPixelResolution);
        double halfHeight = (space.Y_MAX-space.Y_MIN)/(2*secondaryPixelResolution);
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
