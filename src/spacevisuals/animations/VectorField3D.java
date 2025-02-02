package spacevisuals.animations;

import spacevisuals.functions.*;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.Lattice2DHelper2D;
import spacevisuals.spaces.Lattice3DHelper;
import spacevisuals.spaces.helpers.EuclideanSpaceTraverser;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.function.Function;


public class VectorField3D extends PointSetAnimation<Euclidean3D> {

	    EuclideanSpaceTraverser<AbstractSpace> traverser;
        MatrixUtils matrixHelper;
        private double vectorLengthFraction;
        private double vectorLength;
    
        public VectorField3D(Euclidean3D space, int frameSpeed, Function<Double[], Double[]> function, double pixelResolution){
            super(space, frameSpeed, function);
            this.matrixHelper = space.matrixUtils;
            this.vectorLengthFraction = 0.1;
            this.vectorLength = 0.1;
        }

        public void updateAnimation(){
        }

    
        public void handleImage(Double[] input3D, Double[] output){
            /*
             * Add vector lines to list to send to space to draw
             */
            double[] vector = new double[]{output[0]-input3D[0], output[1]-input3D[1], output[2]-input3D[2]};
            double angle = Math.atan2(vector[1], vector[0]);
            vector[0] = Math.cos(angle)*vectorLengthFraction*space.displayScale;
            vector[1] = Math.sin(angle)*vectorLengthFraction*space.displayScale;
            angle = Math.atan2(vector[2], vector[0]);
            vector[1] += Math.sin(angle)*vectorLengthFraction*space.displayScale;
            //double m = Math.hypot(vector[0], vector[1]);
            //vector[0] /= m;
            //vector[1] /= m;
            StdDraw.setPenColor();
            double[] p1 = space.toDrawablePoint(new double[] {input3D[0], input3D[1], input3D[2]});
            double[] p2 = space.toDrawablePoint(new double[]{input3D[0]+vector[0], input3D[1]+vector[1], input3D[2]+vector[2]});
            StdDraw.line(p1[0], p1[1], p2[0], p2[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }

        public void traverseDomain(Consumer<Double[]> handlePoint){
            traverser.traverseDomain(this::handlePoint);
        }


}
    




