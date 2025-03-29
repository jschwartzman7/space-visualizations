package spacevisuals.animations.spacefunctions.functiongraph;

import java.util.function.Consumer;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.AxisTraverserLine;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;

public class Graph2DLine extends SpaceTraverserAnimation<Euclidean2D> {

    private SpaceTraverser<Euclidean2D> traverser;

    public Graph2DLine() {
        super(Euclidean2D.Get(), new AxisTraverserLine(Euclidean2D.Get(), new ConstantResolutionTraverser()));
    }

    @Override
    public void handlePoint(double[] input) {
        double[] output1 = function.apply(new double[]{input[0]});
        double[] output2 = function.apply(new double[]{input[1]});
        if (output1[0] < getSpace().yClipMin || output1[0] > getSpace().yClipMax || output2[0] < getSpace().yClipMin || output2[0] > getSpace().yClipMax){
            return;
        }
        double[] point1 = getSpace().toViewScreenPoint(new double[]{input[0], output1[0]});
        double[] point2 = getSpace().toViewScreenPoint(new double[]{input[1], output2[0]});
        StdDraw.line(point1[0], point1[1], point2[0], point2[1]);
    }
}
