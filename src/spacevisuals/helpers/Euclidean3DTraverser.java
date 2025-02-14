package spacevisuals.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.*;

public class Euclidean3DTraverser extends EuclideanSpaceTraverser<Euclidean3D>{

    public Euclidean3DTraverser(Euclidean3D space){
        super(space);
    }
    public Euclidean3DTraverser(Euclidean3D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xAxisMax-space.xAxisMin)/primaryPixelResolution;
        double yStep = (space.yAxisMax-space.yAxisMin)/primaryPixelResolution;
        double zStep = (space.zAxisMax-space.zAxisMin)/(secondaryPixelResolution);
        double numericXMin = space.xAxisMin;
        double numericXMax = space.xAxisMax;
        double numericYMin = space.yAxisMin;
        double numericYMax = space.yAxisMax;
        double numericZMin = space.zAxisMin;
        double numericZMax = space.zAxisMax;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                for(double z = numericZMin; z <= numericZMax; z += zStep){
                    handlePoint.accept(new double[]{x, y, z});
                }
            }
        }
    }
}
