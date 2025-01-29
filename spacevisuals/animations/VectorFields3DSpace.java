package spacevisuals.animations;

import spacevisuals.functions.*;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.Lattice2DHelper;
import spacevisuals.spaces.Lattice3DHelper;
import edu.princeton.cs.introcs.StdDraw;
import java.util.Hashtable;
import java.util.function.Consumer;
import java.util.function.Function;


public class VectorFields3DSpace extends PointSetAnimation<Euclidean3D> {

	    Lattice3DHelper traverser;
        MatrixUtils matrixHelper;
        private double vectorLengthFraction = 0.005;
    
        public VectorFields3DSpace(Euclidean3D space, int frameSpeed, Function<Double[], Double[]> function, double pixelResolution){
            super(space, frameSpeed, function);
            this.traverser = new Lattice3DHelper(space, pixelResolution);
            this.matrixHelper = space.matrixUtils;
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




            /*double[] newInput = new double[] {input3D[0], input3D[1], input3D[2]};
            double[] newOutput = new double[] {output[0], output[1], output[2]};
            double[] start = matrixHelper.matrixVectorRnxmRn_Rm(space.currentPosition, newInput);
            double[] vectorSum = new double[] {newOutput[0]-newInput[0], newOutput[1]-newInput[1], newOutput[2]-newInput[2]};
            double[] end = matrixHelper.matrixVectorRnxmRn_Rm(space.currentPosition, R3_R3.add(newInput, newOutput));
            double[] start2D = traverser.space.to2D(start);
            double[] end2D = traverser.space.to2D(end);*/
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
    




