package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;
import java.util.function.Function;


public class VectorFields2DSpace extends abstractLatticeAnimation<Euclidean2D> {
    
        public VectorFields2DSpace(Euclidean2D space, int frameSpeed, int resolution, Function<double[], double[]> function){
            super(space, frameSpeed, resolution, function);
        }

        public void handleImage(double[] input, double[] output){
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
            new VectorFields2DSpace(new Euclidean2D(5, 5, true), 25, 50, FunctionR2_R2::f).run();
            // <x+3, yxz, 6z-y>
            //run(inputs)
            
        }
    }
    




