package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;

import spacevisuals.Constants;
import spacevisuals.SpaceUser;
import spacevisuals.spaces.*;
import spacevisuals.spaces.spacetraversers.steppers.*;

public abstract class SpaceTraverser<T extends AbstractSpace> extends SpaceUser<T>{

    public final double DEFAULT_PIXEL_RESOLUTION;
    public double primaryResolution;
    public double secondaryResolution;
    public Stepper stepper;
    

    public SpaceTraverser(T space, Stepper stepper){
        super(space);
        this.DEFAULT_PIXEL_RESOLUTION = Constants.PIXEL_RESOLUTION_HIGH;
        this.primaryResolution = DEFAULT_PIXEL_RESOLUTION;
        this.secondaryResolution = DEFAULT_PIXEL_RESOLUTION;
        this.stepper = stepper;
    }

    public SpaceTraverser(T space, Stepper stepper, double defaultResolution){
        super(space);
        this.DEFAULT_PIXEL_RESOLUTION = defaultResolution;
        this.primaryResolution = defaultResolution;
        this.secondaryResolution = defaultResolution;
        this.stepper = stepper;
    }

    public abstract void traverseDomain(Consumer<double[]> handlePoint);
}
