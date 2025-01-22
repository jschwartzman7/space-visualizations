package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import java.util.function.Function;

public class Euclidean3DGraph extends abstractLatticeAnimation<Euclidean3D> {

    public Euclidean3DGraph(Euclidean3D space, int frameRate, int pixelResolution, Function<double[], double[]> function){
        super(space, frameRate, pixelResolution, function);
    }

    public void drawAnimation(){
        double numericXMin = space.X_MIN*space.xyDistortion;
        double numericXMax = space.X_MAX*space.xyDistortion;
        double numericYMin = space.Y_MIN*space.xyDistortion;
        double numericYMax = space.Y_MAX*space.xyDistortion;
        double xStep = (numericXMax-numericXMin)/this.pixelResolution;
        double yStep = (numericYMax-numericYMin)/this.pixelResolution;
        for(double x = numericXMin; x <= numericXMax; x += xStep){
            for(double y = numericYMin; y <= numericYMax; y += yStep){
                double[] input = new double[]{x, y};
                double[] image = new double[]{function.apply(input)[0]/space.zDistortion};
                handleImage(new double[]{input[0]/space.xyDistortion, input[1]/space.xyDistortion}, image);
            }
        }
    }

    public void handleImage(double[] input, double[] output){
        if (output[0] < space.Z_MIN || output[0] > space.Z_MAX){
            return;
        }
        double[] point = Matrix.matrixVectorMultiplication(space.currentPosition, new double[]{input[0], input[1], output[0]});
        double[] point2D = space.to2D(point);
        StdDraw.setPenColor();
        StdDraw.point(point2D[0], point2D[1]);
    }

    public void updateAnimation(){
        space.updateView();
    }

    public static void main(String[] args) {
        new Euclidean3DGraph(new Euclidean3D(5, 5, true), 25, 100, R2_R.hype2).run();
    }

}
