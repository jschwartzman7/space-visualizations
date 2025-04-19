package spacevisuals.animations.functionanimations.spacetraverseranimations.functiongraphs;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.spaces.spacetraversers.XAxisTraverserLine;
public class Graph2DLine extends SpaceTraverserAnimation {

    public Graph2DLine() {
        super(new XAxisTraverserLine());
    }

    @Override
    public void handlePoint(double[] input) {
        super.handlePoint(input);
    }

    @Override
    public void handleInputOutput(double[] input, double[] output) {
        StdDraw.line(input[0], output[0], input[1], output[1]);
    }
}
