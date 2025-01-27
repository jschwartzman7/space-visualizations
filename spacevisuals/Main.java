package spacevisuals;

import java.util.function.*;
import spacevisuals.animations.*;
import spacevisuals.spaces.*;
import spacevisuals.functions.*;

public class Main {

    public static void main(String[] args) {
        Euclidean2D euclidean2d = new Euclidean2D(10, 1, true);
        Euclidean3D euclidean3d = new Euclidean3D(10, 1, true);
        BasicAnimation<Euclidean2D> basic2DAnimation = new BasicAnimation<Euclidean2D>(euclidean2d, 25);
        BasicAnimation<Euclidean3D> basic3DAnimation = new BasicAnimation<Euclidean3D>(euclidean3d, 25);
        Function<Double[], Double[]> function = input -> new Double[]{input[0]*input[0]-input[1]*input[1]};
        MandelbrotSet mandelbrotSet = new MandelbrotSet(euclidean2d, 25, 300);
        mandelbrotSet.run();
    }
}
