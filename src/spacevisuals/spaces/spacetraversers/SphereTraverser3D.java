package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.SpaceUser3D;

public class SphereTraverser3D extends SpaceTraverser implements SpaceUser3D, ConstantResolutionStepper {
    
    public SphereTraverser3D(int resolution) {
        super(resolution);
    }
    public SphereTraverser3D() {
        super(Constants.PIXEL_RESOLUTION_LOWEST);
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = getStep(2*Math.PI, resolution);
        double maxRadius = Math.sqrt(2)*(Math.max(space().getXRange(), space().getYRange()));
        for(double radius = 0; radius < maxRadius; radius += step) {
            for (double theta = 0; theta < 2*Math.PI; theta += step) {
                for (double phi = 0; phi < Math.PI; phi += step) {
                    double[] point = new double[3];
                    point[0] = radius*(Math.sin(theta) * Math.sin(phi));
                    point[1] = radius*(Math.cos(theta) * Math.sin(phi));
                    point[2] = radius*(Math.cos(phi));
                    handlePoint.accept(point);
                }
            }
        }
        
    }
    
}
