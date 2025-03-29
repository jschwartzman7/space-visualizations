package spacevisuals.animations.spacefunctions.functiongraph;

import java.util.function.Consumer;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.AxisTraverser;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import spacevisuals.Constants;

public class Graph2D extends SpaceTraverserAnimation<Euclidean2D>{


    public Graph2D() {
        super(Euclidean2D.Get(), new AxisTraverser(Euclidean2D.Get(), new ConstantResolutionTraverser()));
    }

    @Override
    public void handlePoint(double[] input) {
        double[] output = function.apply(input);
        if (output[0] < getSpace().yClipMin || output[0] > getSpace().yClipMax){
            return;
        }
        double[] point = getSpace().toViewScreenPoint(new double[]{input[0], output[0]});
        StdDraw.point(point[0], point[1]);
    }
}
