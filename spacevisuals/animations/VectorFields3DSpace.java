package spacevisuals.animations;

import spacevisuals.functions.*;
import spacevisuals.spaces.Euclidean3D;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;
import java.util.function.Function;


public class VectorFields3DSpace extends PointSetAnimation<Lattice3DHelper> {
    
        public VectorFields3DSpace(Euclidean3D space, int frameSpeed, Function<Double[], Double[]> function, double resolution){
            super(space, frameSpeed, function, new Lattice3DHelper(space, resolution));
        }

        public void updateAnimation(){
        }

    
        public void handleImage(Double[] input3D, Double[] output){
            /*
             * Add vector lines to list to send to space to draw
             */
            
            //double[] vector = FunctionR3_R3.subtract(output, input3D);
            //double angle = Math.atan2(vector[1], vector[0]);
            //double m = Math.hypot(vector[0], vector[1]);
            //vector[0] /= m;
            //vector[1] /= m;
            double[] start = Matrix.matrixVectorMultiplication(helper.space.currentPosition, input3D);
            double[] end = Matrix.matrixVectorMultiplication(helper.space.currentPosition, R3_R3.add(input3D, output));
            double[] start2D = helper.space.to2D(start);
            double[] end2D = helper.space.to2D(end);
            StdDraw.setPenColor();
            double range = Math.min(space.X_MAX-space.X_MIN, space.Y_MAX-space.Y_MIN);
            StdDraw.line(start2D[0], start2D[1], end2D[0], end2D[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }
    
        public static void main(String[] args) {
            //System.out.println("HERE: "+args[0]);
            // <x+3, yxz, 6z-y>
            //run(inputs)
            
        }
    }
    




