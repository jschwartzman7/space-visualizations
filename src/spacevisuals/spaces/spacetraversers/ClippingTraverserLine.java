package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import spacevisuals.spaces.*;

public class ClippingTraverserLine extends SpaceTraverser implements SpaceUser2D, ConstantResolutionStepper {

    public ClippingTraverserLine(int resolution) {
        super(resolution);
    }
    public ClippingTraverserLine() {
        super(Constants.PIXEL_RESOLUTION_LOW);
    }
    /*
     * handlePoint acceps coordinates of a line segment
     */
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xClipMax-space().xClipMin, this.resolution);
        double yStep = getStep(space().yClipMax-space().yClipMin, this.resolution);
        for(double x = space().xClipMin; x <= space().xClipMax; x += xStep){
            for(double y = space().yClipMin; y <= space().yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, x+xStep, y, y});
                handlePoint.accept(new double[]{x, x, y, y+yStep});
                handlePoint.accept(new double[]{x, x+xStep, y+yStep, y+yStep});
                handlePoint.accept(new double[]{x+xStep, x+xStep, y, y+yStep});
            }
        }
    }
}
