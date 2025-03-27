package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.spaces.*;

public class DiskClippingTraverser extends SpaceTraverser<Euclidean2D>{


    public DiskClippingTraverser(Euclidean2D space, Stepper stepper){
        super(space, stepper);
    }
    public DiskClippingTraverser(Euclidean2D space, Stepper stepper, double defaultPixelResolution){
        super(space, stepper, defaultPixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        double radiusMax = Math.hypot(getSpace().getXRange(), getSpace().getYRange())/2;
        double radiusStep = stepper.getStep(Math.min(getSpace().xClipMax-getSpace().xClipMin, getSpace().yClipMax-getSpace().yClipMin), primaryResolution);
        double angleStep = stepper.getStep(Math.min(getSpace().xClipMax-getSpace().xClipMin, getSpace().yClipMax-getSpace().yClipMin), secondaryResolution);
        for(double radius = 0; radius <= radiusMax; radius += radiusStep){
            for(double angle = 0; angle < 2*Math.PI; angle += angleStep){
                double x = radius*Math.cos(angle);
                double y = radius*Math.sin(angle);
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
