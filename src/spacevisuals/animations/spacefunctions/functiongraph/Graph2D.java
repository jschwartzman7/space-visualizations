package spacevisuals.animations.spacefunctions.functiongraph;

import java.util.function.Consumer;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.SpaceFunction;

public class Graph2D extends SpaceFunction<Euclidean2D> implements PointSetAnimation {

    private double xStep = 0.003;

    public Graph2D() {
        super(Euclidean2D.Get());
    }

    @Override
    public void traverseDomain(Consumer<double[]> handlePoint) {
        for(double x = space.xClipMin; x < space.xClipMax; x += xStep){
            handlePoint.accept(new double[]{x, 0});
        }
    }

    @Override
    public void handlePoint(double[] input) {
        double[] output = function.apply(input);
        if (output[0] < space.yClipMin || output[0] > space.yClipMax){
            return;
        }
        double[] point = space.toViewScreenPoint(new double[]{input[0], output[0]});
        StdDraw.filledCircle(point[0], point[1], 0.005);
    }
    @Override
    public void configureAnimation(String[] parameters) {
        setCustomFunctionStringArray(parameters);
    }
}
