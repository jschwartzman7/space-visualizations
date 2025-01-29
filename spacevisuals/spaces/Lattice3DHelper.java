package spacevisuals.spaces;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.*;

public class Lattice3DHelper extends EuclideanSpaceTraverser<Euclidean3D>{

    public Lattice3DHelper(Euclidean3D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<Double[]> handlePoint){
        double xStep = (space.X_MAX-space.X_MIN)/(primaryPixelResolution*space.primaryDistortion);
        double yStep = (space.Y_MAX-space.Y_MIN)/(primaryPixelResolution*space.primaryDistortion);
        double zStep = (space.Z_MAX-space.Z_MIN)/(secondaryPixelResolution*space.secondaryDistortion);
        double numericXMin = space.X_MIN*space.primaryDistortion;
        double numericXMax = space.X_MAX*space.primaryDistortion;
        double numericYMin = space.Y_MIN*space.primaryDistortion;
        double numericYMax = space.Y_MAX*space.primaryDistortion;
        double numericZMin = space.Y_MIN*space.secondaryDistortion;
        double numericZMax = space.Y_MAX*space.secondaryDistortion;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                for(double z = numericZMin; z <= numericZMax; z += zStep){
                    handlePoint.accept(new Double[]{x, y, z});
                }
            }
        }
    }
}
