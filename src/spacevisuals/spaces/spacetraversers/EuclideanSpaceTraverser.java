package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.AbstractSpace;

public abstract class EuclideanSpaceTraverser<T extends AbstractSpace> {

    public T space;
    public static final int DEFAULT_PIXEL_RESOLUTION = 50;
    public double primaryPixelResolution;
    public double secondaryPixelResolution;


    public EuclideanSpaceTraverser(T space){
        this.space = space;
        this.primaryPixelResolution = DEFAULT_PIXEL_RESOLUTION;
        this.secondaryPixelResolution = DEFAULT_PIXEL_RESOLUTION;
    }
    public EuclideanSpaceTraverser(T space, double pixelResolution){
        this.space = space;
        this.primaryPixelResolution = pixelResolution;
        this.secondaryPixelResolution = pixelResolution;
    }

    abstract void traverseDomain(Consumer<double[]> handlePoint);
}
