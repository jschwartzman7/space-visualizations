package spacevisuals.spaces;

import java.util.function.Consumer;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;

public class Lattice2DHelper3D  extends EuclideanSpaceTraverser<Euclidean3D>{

    public Lattice2DHelper3D(Euclidean3D space, double pixelResolution){
        super(space, pixelResolution);
    }

    public void traverseDomain(Consumer<Double[]> handlePoint){
        double xStep = (space.X_MAX-space.X_MIN)*space.primaryDistortion/primaryPixelResolution;
        double yStep = (space.Y_MAX-space.Y_MIN)*space.primaryDistortion/primaryPixelResolution;
        double numericXMin = space.X_MIN*space.primaryDistortion;
        double numericXMax = space.X_MAX*space.primaryDistortion;
        double numericYMin = space.Y_MIN*space.primaryDistortion;
        double numericYMax = space.Y_MAX*space.primaryDistortion;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                handlePoint.accept(new Double[]{x, y});
            }
        }
    }
}
