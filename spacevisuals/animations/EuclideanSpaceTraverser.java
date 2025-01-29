package spacevisuals.animations;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;

public abstract class EuclideanSpaceTraverser<ConcreteSpace extends AbstractSpace> {

    ConcreteSpace space;
    double primaryPixelResolution;
    double secondaryPixelResolution;

    public EuclideanSpaceTraverser(ConcreteSpace space, double pixelResolution){
        this.space = space;
        this.primaryPixelResolution = pixelResolution;
        this.secondaryPixelResolution = pixelResolution;
    }

    abstract void traverseDomain(Consumer<Double[]> handlePoint);
}
