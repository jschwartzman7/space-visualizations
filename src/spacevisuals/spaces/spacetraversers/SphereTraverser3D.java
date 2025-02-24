package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class SphereTraverser3D extends SpaceTraverser<Euclidean3D>{

    public static final double DEFAULT_PIXEL_RESOLUTION = 100;

    public SphereTraverser3D(Euclidean3D space){
        super(space, DEFAULT_PIXEL_RESOLUTION);
        this.stepper = new ConstantResolutionTraverser();
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        
    }
}
