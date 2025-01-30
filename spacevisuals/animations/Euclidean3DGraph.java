package spacevisuals.animations;


import spacevisuals.functions.MatrixUtils;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.Lattice2DHelper3D;
import edu.princeton.cs.introcs.StdDraw;

import java.util.function.Consumer;
import java.util.function.Function;

public class Euclidean3DGraph extends PointSetAnimation<Euclidean3D> {

    Lattice2DHelper3D traverser;
    MatrixUtils matrixHelper;

    public Euclidean3DGraph(Euclidean3D space, int frameRate, Function<Double[], Double[]> function, int pixelResolution){
        super(space, frameRate, function);
        this.traverser = new Lattice2DHelper3D(space, pixelResolution);
        this.matrixHelper = space.matrixUtils;
    }

    public void traverseDomain(Consumer<Double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }

    @Override
    public void handleImage(Double[] input, Double[] output){
        double outpuValue = output[0];
        if (outpuValue < space.Z_MIN*space.secondaryDistortion || outpuValue > space.Z_MAX*space.secondaryDistortion){
            return;
        }
        double[] point = space.toDrawablePoint(new double[]{input[0], input[1], outpuValue});
        StdDraw.setPenColor();
        StdDraw.point(point[0], point[1]);
    }

}
