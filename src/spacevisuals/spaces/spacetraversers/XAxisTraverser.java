package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class XAxisTraverser extends SpaceTraverser implements SpaceUser2D, ConstantResolutionStepper {

    public XAxisTraverser(int resolution){
        super(resolution);
    }
    public XAxisTraverser(){
        super(Constants.PIXEL_RESOLUTION_HIGH);
    }

    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = getStep(space().xClipMax-space().xClipMin, this.resolution);
        for(double x = space().xClipMin; x <= space().xClipMax; x += step){
                handlePoint.accept(new double[]{x});
        }
    }
}
