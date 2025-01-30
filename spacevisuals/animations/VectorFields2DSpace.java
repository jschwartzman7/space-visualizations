package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Lattice2DHelper2D;

import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.function.Function;


public class VectorFields2DSpace extends PointSetAnimation<Euclidean2D> {
    
	    Lattice2DHelper2D<Euclidean2D> traverser;

        public VectorFields2DSpace(Euclidean2D space, int frameSpeed, Function<Double[], Double[]> function, double pixelResolution){
            super(space, frameSpeed, function);
            this.traverser = new Lattice2DHelper2D<Euclidean2D>(space, pixelResolution);
        }

        public void handleImage(Double[] input, Double[] output){
            /*
             * Add vector lines to list to send to space to draw
             */
            double[] vector = new double[]{output[0]-input[0], output[1]-input[1]};
            double angle = Math.atan2(vector[1], vector[0]);
            double m = Math.hypot(vector[0], vector[1]);
            //vector[0] /= m;
            //vector[1] /= m;
            double range = Math.min(space.X_MAX-space.X_MIN, space.Y_MAX-space.Y_MIN);
            StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
    
        }

        public void traverseDomain(Consumer<Double[]> handlePoint){
            traverser.traverseDomain(this::handlePoint);
        }
    }
    




