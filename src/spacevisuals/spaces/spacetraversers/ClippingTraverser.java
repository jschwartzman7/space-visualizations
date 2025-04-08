package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class ClippingTraverser implements SpaceUser2D, SpaceTraverser, ConstantResolutionStepper {

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xClipMax-space().xClipMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        double yStep = getStep(space().yClipMax-space().yClipMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        for(double x = space().xClipMin; x <= space().xClipMax; x += xStep){
            for(double y = space().yClipMin; y <= space().yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
}
