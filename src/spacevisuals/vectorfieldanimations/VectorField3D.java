package spacevisuals.vectorfieldanimations;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.Euclidean3DSpaceTraverser;
import spacevisuals.PointSetAnimation;
import spacevisuals.functions.*;
import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField3D extends PointSetAnimation<Euclidean3D> {

        Matrix3D matrixHelper;
        private double vectorLengthFraction;
        private double vectorLength;
        Function<double[], double[]> function;
	    Euclidean3DSpaceTraverser traverser;
        Euclidean3D space;
    
        public VectorField3D(Euclidean3D space, Function<double[], double[]> function){
            super(space, function);
            this.matrixHelper = space.matrixUtils;
            this.vectorLengthFraction = 0.1;
            this.vectorLength = 0.1;
            this.traverser = new Euclidean3DSpaceTraverser(space);
        }

        public void updateAnimation(){
        }

    
        public void handlePoint(double[] input3D){
            /*
             * Add vector lines to list to send to space to draw
             */
            double[] output = function.apply(input3D);
            double[] vector = new double[]{output[0]-input3D[0], output[1]-input3D[1], output[2]-input3D[2]};
            double angle = Math.atan2(vector[1], vector[0]);
            double xClipRange = space.xClipMax-space.xClipMin;
            double yClipRange = space.yClipMax-space.yClipMin;
            vector[0] = Math.cos(angle)*vectorLengthFraction*xClipRange;
            vector[1] = Math.sin(angle)*vectorLengthFraction*xClipRange;
            angle = Math.atan2(vector[2], vector[0]);
            vector[1] += Math.sin(angle)*vectorLengthFraction*yClipRange;
            //double m = Math.hypot(vector[0], vector[1]);
            //vector[0] /= m;
            //vector[1] /= m;
            StdDraw.setPenColor();
            double[] p1 = space.toViewScreenPoint(new double[] {input3D[0], input3D[1], input3D[2]});
            double[] p2 = space.toViewScreenPoint(new double[]{input3D[0]+vector[0], input3D[1]+vector[1], input3D[2]+vector[2]});
            StdDraw.line(p1[0], p1[1], p2[0], p2[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }

        public void traverseDomain(Consumer<double[]> handlePoint){
            traverser.traverseDomain(handlePoint);
        }


}
    




