package spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.XAxisTraverserLine;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionStepper;
import spacevisuals.utils.Traverser;

public class Graph2DLine extends SpaceTraverserAnimation {

    private Traverser traverser;

    public Graph2DLine() {
        super(new XAxisTraverserLine());
    }

    @Override
    public void handlePoint(double[] input) {
        double[] output1 = function.apply(new double[]{input[0]});
        double[] output2 = function.apply(new double[]{input[1]});
        if (output1[0] < Euclidean2D.Get().yClipMin || output1[0] > Euclidean2D.Get().yClipMax || output2[0] < Euclidean2D.Get().yClipMin || output2[0] > Euclidean2D.Get().yClipMax){
            return;
        }
        double[] point1 = Euclidean2D.Get().toViewScreenPoint(new double[]{input[0], output1[0]});
        double[] point2 = Euclidean2D.Get().toViewScreenPoint(new double[]{input[1], output2[0]});
        StdDraw.line(point1[0], point1[1], point2[0], point2[1]);
    }
}
