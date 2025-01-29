package spacevisuals.animations;


import spacevisuals.functions.MatrixUtils;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.Lattice2DHelper;
import edu.princeton.cs.introcs.StdDraw;

import java.util.function.Consumer;
import java.util.function.Function;

public class Euclidean3DGraph extends PointSetAnimation<Euclidean3D> {

    Lattice2DHelper<Euclidean3D> traverser;
    MatrixUtils matrixHelper;

    public Euclidean3DGraph(Euclidean3D space, int frameRate, Function<Double[], Double[]> function, int pixelResolution){
        super(space, frameRate, function);
        this.traverser = new Lattice2DHelper<Euclidean3D>(space, pixelResolution);
        this.matrixHelper = space.matrixUtils;
    }

    public void traverseDomain(Consumer<Double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }

    @Override
    public void handleImage(Double[] input, Double[] output){
        if (output[0] < traverser.space.Z_MIN || output[0] > traverser.space.Z_MAX){
            return;
        }
        double[] point = space.toDrawablePoint(new double[]{input[0], input[1], output[0]});
        StdDraw.setPenColor();
        StdDraw.point(point[0], point[1]);
    }

}
