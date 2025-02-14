package spacevisuals.pointsetanimations;


import spacevisuals.*;
import spacevisuals.functions.MatrixUtils;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.helpers.Euclidean3DTraverser2D;
import edu.princeton.cs.introcs.StdDraw;

import java.util.function.Consumer;
import java.util.function.Function;

public class Euclidean3DGraph implements PointSetAnimation {

    Euclidean3DTraverser2D traverser;
    Function<double[], double[]> function;
    Euclidean3D space;

    public Euclidean3DGraph(Euclidean3D space, Function<double[], double[]> function){
        this.space = space;
        this.traverser = new Euclidean3DTraverser2D(space);
        this.function = function;
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
