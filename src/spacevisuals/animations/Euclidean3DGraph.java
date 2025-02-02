package spacevisuals.animations;


import spacevisuals.functions.MatrixUtils;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.helpers.Lattice2DHelper3D;
import edu.princeton.cs.introcs.StdDraw;

import java.util.function.Consumer;
import java.util.function.Function;

public class Euclidean3DGraph extends PointSetAnimation<Euclidean3D> {

    Lattice2DHelper3D traverser;

    public Euclidean3DGraph(Euclidean3D space, int frameRate, Function<double[], double[]> function, int pixelResolution){
        super(space, frameRate, function);
        this.traverser = new Lattice2DHelper3D(space, pixelResolution);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }

    @Override
    public void handleImage(double[] input, double[] output){
        double outpuValue = output[0];
        if (outpuValue < space.zAxisMin || outpuValue > space.zAxisMax){
            return;
        }
        double[] point = space.toDrawablePoint(new double[]{input[0], input[1], outpuValue});
        StdDraw.setPenColor();
        StdDraw.point(point[0], point[1]);
    }

}
