package spacevisuals.spaces.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;

public abstract class EuclideanSpaceTraverser<ConcreteSpace extends AbstractSpace> {

    public ConcreteSpace space;
    public double primaryPixelResolution;
    public double secondaryPixelResolution;

    public EuclideanSpaceTraverser(ConcreteSpace space, double pixelResolution){
        this.space = space;
        this.primaryPixelResolution = pixelResolution;
        this.secondaryPixelResolution = pixelResolution;
    }

    abstract void traverseDomain(Consumer<double[]> handlePoint);
}
