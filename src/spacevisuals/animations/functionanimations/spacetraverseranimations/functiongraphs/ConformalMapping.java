package spacevisuals.animations.functionanimations.spacetraverseranimations.functiongraphs;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.ClippingTraverserLine;

public class ConformalMapping extends SpaceTraverserAnimation implements SpaceUser2D{

    public ConformalMapping() {
        super(new ClippingTraverserLine());
    }

    @Override
    public void handlePoint(double[] input) {
        double[] p1 = new double[]{input[0], input[2]};
        double[] p2 = new double[]{input[1], input[3]};
        super.handlePoint(p1);
        super.handlePoint(p2);
    }

    @Override
    public void handleInputOutput(double[] input, double[] output) {
        StdDraw.line(input[0], input[1], output[0], output[1]);;
    }
    
}
