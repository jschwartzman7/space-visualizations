package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionStepper;
import spacevisuals.utils.Constants;

public class XAxisTraverserLine extends SpaceTraverser implements SpaceUser2D, ConstantResolutionStepper {
    
    public XAxisTraverserLine(int resolution){
        super(resolution);
    }
    public XAxisTraverserLine(){
        super(Constants.PIXEL_RESOLUTION_MEDIUM);
    }
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        double step = getStep(space().getXRange(), this.resolution);
        for(double x = space().xClipMin; x <= space().xClipMax; x += step){
            handlePoint.accept(new double[]{x, x+step});
        }
    }
}
