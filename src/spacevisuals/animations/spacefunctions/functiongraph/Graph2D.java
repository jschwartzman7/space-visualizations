package spacevisuals.animations.spacefunctions.functiongraph;


import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.XAxisTraverser;

public class Graph2D extends SpaceTraverserAnimation{


    public Graph2D() {
        super(new XAxisTraverser());
    }

    @Override
    public void handlePoint(double[] input) {
        double[] output = function.apply(input);
        if (output[0] < Euclidean2D.Get().yClipMin || output[0] > Euclidean2D.Get().yClipMax){
            return;
        }
        double[] point = Euclidean2D.Get().toViewScreenPoint(new double[]{input[0], output[0]});
        StdDraw.point(point[0], point[1]);
    }
}
