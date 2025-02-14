package spacevisuals.helpers;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean3D;

public abstract class EuclideanSpaceTraverser<ConcreteSpace extends AbstractSpace> {

    public ConcreteSpace space;
    public static final int DEFAULT_PIXEL_RESOLUTION = 50;
    public double primaryPixelResolution;
    public double secondaryPixelResolution;


    public EuclideanSpaceTraverser(ConcreteSpace space){
        this.space = space;
        this.primaryPixelResolution = DEFAULT_PIXEL_RESOLUTION;
        this.secondaryPixelResolution = DEFAULT_PIXEL_RESOLUTION;
    }
    public EuclideanSpaceTraverser(ConcreteSpace space, double pixelResolution){
        this.space = space;
        this.primaryPixelResolution = pixelResolution;
        this.secondaryPixelResolution = pixelResolution;
    }

    abstract void traverseDomain(Consumer<double[]> handlePoint);
}
