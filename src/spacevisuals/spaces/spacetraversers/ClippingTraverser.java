package spacevisuals.spaces.spacetraversers;

import java.awt.Color;
import java.util.function.Consumer;
import spacevisuals.spaces.spacetraversers.steppers.*;
import spacevisuals.utils.Constants;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.*;

public class ClippingTraverser implements SpaceUser2D, SpaceTraverser, ConstantResolutionTraverser {

    public void traverseDomain(Consumer<double[]> handlePoint){
        double xStep = getStep(space().xClipMax-space().xClipMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        double yStep = getStep(space().yClipMax-space().yClipMin, Constants.PIXEL_RESOLUTION_MEDIUM);
        for(double x = space().xClipMin; x <= space().xClipMax; x += xStep){
            for(double y = space().yClipMin; y <= space().yClipMax; y += yStep){
                handlePoint.accept(new double[]{x, y});
            }
        }
    }
    public void drawPointRectangle(double[] point, Color color){
        double halfWidth = getStep(space().xClipMax-space().xClipMin, Constants.PIXEL_RESOLUTION_MEDIUM)/2;
        double halfHeight = getStep(space().yClipMax-space().yClipMin, Constants.PIXEL_RESOLUTION_MEDIUM)/2;
        StdDraw.setPenColor(color);
        StdDraw.filledRectangle(point[0], point[1], halfWidth, halfHeight);
    }
}
