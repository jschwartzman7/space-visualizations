package spacevisuals.spaces.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.*;

public class Lattice3DHelper extends EuclideanSpaceTraverser<Euclidean3D>{

    public Lattice3DHelper(Euclidean3D space, double pixelResolution){
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
