package spacevisuals.pointsetanimations;


import spacevisuals.*;
import spacevisuals.functions.MatrixUtils;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.helpers.Euclidean3DTraverser2D;
import edu.princeton.cs.introcs.StdDraw;

import java.util.function.Consumer;
import java.util.function.Function;

public class Euclidean3DGraph extends PointSetAnimation<Euclidean3D> {

    Euclidean3DTraverser2D traverser;

    public Euclidean3DGraph(Euclidean3D space, Function<double[], double[]> function){
        super(space, function);
        this.traverser = new Euclidean3DTraverser2D(space);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }

    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if (output[0] < space.zAxisMin || output[0] > space.zAxisMax){
            return;
        }
        double[] point = space.toViewScreenPoint(new double[]{input[0], input[1], output[0]});
        StdDraw.setPenColor();
        StdDraw.point(point[0], point[1]);
    }

}
