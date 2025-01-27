package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean2D;

import java.util.Hashtable;
import java.util.function.Function;


public class VectorFields2DSpace extends PointSetAnimation<Lattice2DHelper> {
    
        public VectorFields2DSpace(Euclidean2D space, int frameSpeed, double pixelResolution, Function<Double[], Double[]> function){
            super(space, frameSpeed, function, new Lattice2DHelper(space, pixelResolution));
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
    
        public void updateAnimation(){
            
        }
    
    
        public static void main(String[] args) {
            //System.out.println("HERE: "+args[0]);
            // <x+3, yxz, 6z-y>
            //run(inputs)
            
        }
    }
    




