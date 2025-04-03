package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class XAxisTraverser implements SpaceUser2D, SpaceTraverser, ConstantResolutionStepper {

    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = getStep(space().xClipMax-space().xClipMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        for(double x = space().xClipMin; x <= space().xClipMax; x += step){
                handlePoint.accept(new double[]{x});
        }
    }
}
