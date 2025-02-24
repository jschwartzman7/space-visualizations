package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.*;
import spacevisuals.spaces.spacetraversers.steppers.*;

public abstract class SpaceTraverser<T extends AbstractSpace> extends SpaceUser<T>{

    public final double DEFAULT_PIXEL_RESOLUTION;
    public Stepper stepper;
    public double primaryPixelResolution;
    public double secondaryPixelResolution;


    public SpaceTraverser(T space, double defaultPixelResolution){
        super(space);
        this.DEFAULT_PIXEL_RESOLUTION = defaultPixelResolution;
        this.primaryPixelResolution = defaultPixelResolution;
        this.secondaryPixelResolution = defaultPixelResolution;
    }
    public abstract void traverseDomain(Consumer<double[]> handlePoint);
    
}
