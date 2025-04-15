package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class ClippingTraverser extends SpaceTraverser implements SpaceUser2D, ConstantResolutionStepper {

    public ClippingTraverser(int resolution) {
        super(resolution);
    }
    public ClippingTraverser() {
        super(Constants.PIXEL_RESOLUTION_LOW);
    }
    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xClipMax-space().xClipMin, this.resolution);
        double yStep = getStep(space().yClipMax-space().yClipMin, this.resolution);
        for(double x = space().xClipMin; x <= space().xClipMax; x += xStep){
            for(double y = space().yClipMin; y <= space().yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
