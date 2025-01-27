package spacevisuals.animations;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class Lattice2DHelper<ConcreteSpace extends AbstractSpace> implements PointSetHelper{
    
    ConcreteSpace space;
    double primarPixelResolution;
    double secondaryPixelResolution;

    public Lattice2DHelper(ConcreteSpace space, double pixelResolution){
        this.space = space;
        this.primarPixelResolution = pixelResolution;
        this.secondaryPixelResolution = pixelResolution;
    }

    public void traverseDomain(Consumer<Double[]> handlePoint){
        double xStep = (space.X_MAX-space.X_MIN)/(primarPixelResolution*space.primaryDistortion);
        double yStep = (space.Y_MAX-space.Y_MIN)/(secondaryPixelResolution*space.secondaryDistortion);
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
    public void drawPoint(double[] numericPoint, Color color){
        double[] point = space.toPoint(numericPoint);
        double halfWidth = (space.X_MAX-space.X_MIN)/(2*primarPixelResolution*space.primaryDistortion);
        double halfHeight = (space.Y_MAX-space.Y_MIN)/(2*secondaryPixelResolution*space.secondaryDistortion);
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
