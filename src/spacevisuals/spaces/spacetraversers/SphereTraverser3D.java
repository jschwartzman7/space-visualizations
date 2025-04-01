package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser3D;

public class SphereTraverser3D implements SpaceTraverser, Stepper {
    public double getStep(){
        return 0;
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
    }
    @Override
    public double getStep(double axisRange, double pixelResolution) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStep'");
    }
}
