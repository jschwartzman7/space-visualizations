package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.*;

public class Euclidean3DSpaceTraverser extends EuclideanSpaceTraverser<Euclidean3D>{

    public Euclidean3DSpaceTraverser(Euclidean3D space){
        super(space);
    }
    public Euclidean3DSpaceTraverser(Euclidean3D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = (space.xAxisMax-space.xAxisMin)/primaryPixelResolution;
        double yStep = (space.yAxisMax-space.yAxisMin)/primaryPixelResolution;
        double zStep = (space.zAxisMax-space.zAxisMin)/secondaryPixelResolution;
        for(double x = space.xAxisMin; x <= space.xAxisMax; x += xStep){
            for(double y = space.yAxisMin; y <= space.yAxisMax; y += yStep){
                for(double z = space.zAxisMin; z <= space.zAxisMax; z += zStep){
                    handlePoint.accept(new double[]{x, y, z});
                }
            }
        }
    }
}
