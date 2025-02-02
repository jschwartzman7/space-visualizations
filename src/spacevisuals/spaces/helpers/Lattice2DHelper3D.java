package spacevisuals.spaces.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;

public class Lattice2DHelper3D  extends EuclideanSpaceTraverser<Euclidean3D>{

    public Lattice2DHelper3D(Euclidean3D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xAxisMax-space.xAxisMin)/primaryPixelResolution;
        double yStep = (space.yAxisMax-space.yAxisMin)/secondaryPixelResolution;
        double numericXMin = space.xAxisMin;
        double numericXMax = space.xAxisMax;
        double numericYMin = space.yAxisMin;
        double numericYMax = space.yAxisMax;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
