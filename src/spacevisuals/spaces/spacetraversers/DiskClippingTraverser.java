package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class DiskClippingTraverser extends SpaceTraverser implements SpaceUser2D, ConstantResolutionStepper {

    public DiskClippingTraverser(int resolution) {
        super(resolution);
    }
    public DiskClippingTraverser() {
        super(Constants.PIXEL_RESOLUTION_MEDIUM);
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(space().getXRange(), space().getYRange())/2;
        double radiusStep = getStep(Math.min(space().xClipMax-space().xClipMin, space().yClipMax-space().yClipMin), this.resolution);
        double angleStep = getStep(Math.min(space().xClipMax-space().xClipMin, space().yClipMax-space().yClipMin), this.resolution);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
