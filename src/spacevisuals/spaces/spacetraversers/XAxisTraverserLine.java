package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionStepper;
import spacevisuals.utils.Constants;

public class XAxisTraverserLine implements SpaceUser2D, SpaceTraverser, ConstantResolutionStepper {
    
    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = getStep(space().xClipMax-space().xClipMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        for(double x = space().xClipMin; x <= space().xClipMax; x += step){
            handlePoint.accept(new double[]{x, x+step});
        }
    }
}
