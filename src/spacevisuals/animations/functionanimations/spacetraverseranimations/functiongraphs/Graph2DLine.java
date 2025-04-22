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
        double[] output = new double[]{applyFunction(new double[]{input[0]})[0], applyFunction(new double[]{input[1]})[0]};
        if(output == null || output.length == 0){
            return;
        }
        for(double value : output){
            if(Double.isNaN(value) || Double.isInfinite(value)){
                return;
            }
        }
        handleInputOutput(input, output);
    }

    @Override
    public void handleInputOutput(double[] input, double[] output) {
        StdDraw.line(input[0], output[0], input[1], output[1]);
    }
}
