package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;

public class SphereTraverser3D extends SpaceTraverser<Euclidean3D>{

    public SphereTraverser3D(Euclidean3D space, Stepper stepper){
        super(space, stepper);
    }
    public SphereTraverser3D(Euclidean3D space, Stepper stepper, double defaultPixelResolution){
        super(space, stepper, defaultPixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        
    }
}
