package spacevisuals.animations.vectorfieldanimations;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.axisintervals.IntervalsRange;
import spacevisuals.spaces.spacetraversers.Euclidean3DSpaceTraverser;
import spacevisuals.Animation3DSpace;
import spacevisuals.PointSetAnimation;
import spacevisuals.functions.*;
import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField3D extends Animation3DSpace implements PointSetAnimation {

        Matrix3D matrixHelper;
	    Euclidean3DSpaceTraverser traverser;
        private IntervalsRange vectorSizer;
    
        public VectorField3D(){
            super();
            this.matrixHelper = space.matrixUtils;
            this.traverser = new Euclidean3DSpaceTraverser(space, 5);
            this.vectorSizer = new IntervalsRange(new double[]{5}, new double[][]{new double[]{6, 15}});
    
        }
        public VectorField3D(Function<double[], double[]> function){
            super(function);
            this.matrixHelper = space.matrixUtils;
            this.traverser = new Euclidean3DSpaceTraverser(space, 5);
            this.vectorSizer = new IntervalsRange(new double[]{5}, new double[][]{new double[]{6, 15}});
    
        }

        public void updateAnimation(){
            vectorSizer.updateLabelInterval(0, space.xAxisMax-space.xAxisMin);
        }

    
        public void handlePoint(double[] input3D){
            /*
             * Add vector lines to list to send to space to draw
             */
            double[] output = function.apply(input3D);
            double vectorMagnitude = Rn_R.magnitude(output);
            if(vectorMagnitude == 0){
                return;
            }
            double[] unitVector = new double[]{vectorSizer.labelIntervals[0]*output[0]/vectorMagnitude, vectorSizer.labelIntervals[0]*output[1]/vectorMagnitude, vectorSizer.labelIntervals[0]*output[2]/vectorMagnitude};
            StdDraw.setPenColor();
            double[] p1 = space.toViewScreenPoint(new double[] {input3D[0], input3D[1], input3D[2]});
            double[] p2 = space.toViewScreenPoint(new double[]{input3D[0]+unitVector[0], input3D[1]+unitVector[1], input3D[2]+unitVector[2]});
            StdDraw.line(p1[0], p1[1], p2[0], p2[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }

        public void traverseDomain(Consumer<double[]> handlePoint){
            traverser.traverseDomain(handlePoint);
        }


}
    




