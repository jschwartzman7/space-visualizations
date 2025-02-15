package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;

public class Euclidean3DPlaneTraverser  extends EuclideanSpaceTraverser<Euclidean3D>{

    public Euclidean3DPlaneTraverser(Euclidean3D space){
        super(space);
    }
    public Euclidean3DPlaneTraverser(Euclidean3D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xAxisMax-space.xAxisMin)/primaryPixelResolution;
        double yStep = (space.yAxisMax-space.yAxisMin)/secondaryPixelResolution;
        for(double x = space.xAxisMin; x <= space.xAxisMax; x += xStep){
            for(double y = space.yAxisMin; y <= space.yAxisMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
