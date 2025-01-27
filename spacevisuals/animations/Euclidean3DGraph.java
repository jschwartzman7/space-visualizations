package spacevisuals.animations;


import spacevisuals.functions.Matrix;
import spacevisuals.functions.R2_R;
import spacevisuals.spaces.Euclidean3D;
import edu.princeton.cs.introcs.StdDraw;
import java.util.function.Function;

public class Euclidean3DGraph extends PointSetAnimation<Lattice2DHelper<Euclidean3D>>{

    public Euclidean3DGraph(Euclidean3D space, int frameRate, Function<Double[], Double[]> function, int pixelResolution){
        super(space, frameRate, function, new Lattice2DHelper<Euclidean3D>(space, pixelResolution));
    }

    @Override
    public void handleImage(Double[] input, Double[] output){
        if (output[0] < traverser.space.Z_MIN || output[0] > traverser.space.Z_MAX){
            return;
        }
        double[] point = Matrix.matrixVectorMultiplication(traverser.space.currentPosition, new double[]{input[0], input[1], output[0]});
        double[] point2D = traverser.space.to2D(point);
        StdDraw.setPenColor();
        StdDraw.point(point2D[0], point2D[1]);
    }

}
