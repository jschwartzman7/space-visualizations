package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.spacetraversers.steppers.*;

public abstract class EuclideanSpaceTraverser<T extends AbstractSpace> {

    public T space;
    public final double DEFAULT_PIXEL_RESOLUTION;
    public Stepper stepper;
    public double primaryPixelResolution;
    public double secondaryPixelResolution;


    public EuclideanSpaceTraverser(T space, double defaultPixelResolution){
        this.space = space;
        this.DEFAULT_PIXEL_RESOLUTION = defaultPixelResolution;
        this.primaryPixelResolution = defaultPixelResolution;
        this.secondaryPixelResolution = defaultPixelResolution;
    }
    public abstract void traverseDomain(Consumer<double[]> handlePoint);
    
}
