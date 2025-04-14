package spacevisuals.animations.spacefunctions.spacetraverseranimations.functiongraphs;


import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.XAxisTraverser;

public class Graph2D extends SpaceTraverserAnimation implements SpaceUser2D{

    public Graph2D() {
        super(new XAxisTraverser());
    }

    @Override
    public void handlePoint(double[] input) {
        double[] output = function.apply(input);
        if (output[0] < space().yClipMin || output[0] > space().yClipMax){
            return;
        }
        double[] point = space().toViewScreenPoint(new double[]{input[0], output[0]});
        StdDraw.point(point[0], point[1]);
    }
}
