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
        super(Constants.PIXEL_RESOLUTION_LOW);
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
        double maxRadius = Math.hypot(space().xAxisMax-space().xAxisMin, space().yAxisMax-space().yAxisMin)/2;
        double angleStep = getStep(2*Math.PI, resolution);
        double radiusStep = getStep(maxRadius, resolution);
        for(double radius = 0; radius < maxRadius; radius += radiusStep) {
            for (double theta = 0; theta < 2*Math.PI; theta += angleStep) {
                for (double phi = 0; phi < Math.PI; phi += angleStep) {
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
