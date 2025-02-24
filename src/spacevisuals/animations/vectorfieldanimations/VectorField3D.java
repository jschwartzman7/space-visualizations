package spacevisuals.animations.vectorfieldanimations;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.intervalranges.IntervalsRange;
import spacevisuals.SpaceFunction3D;
import spacevisuals.spaces.spacetraversers.PrismTraverser3D;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.functions.*;
import spacevisuals.functions.functionhandling.FunctionsEnum;

import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField3D extends SpaceFunction3D implements PointSetAnimation {

        private Matrix3D matrixHelper;
	    private SpaceTraverser<Euclidean3D> traverser;
        private IntervalsRange vectorSizer;
    
        public VectorField3D(){
            super();
            this.matrixHelper = space.matrixUtils;
            this.traverser = new PrismTraverser3D(space, 5);
            this.vectorSizer = new IntervalsRange(new double[]{5}, new double[][]{new double[]{6, 15}});
    
        }
        public VectorField3D(Function<double[], double[]> function){
            super(function);
            this.matrixHelper = space.matrixUtils;
            this.traverser = new PrismTraverser3D(space, 5);
            this.vectorSizer = new IntervalsRange(new double[]{5}, new double[][]{new double[]{6, 15}});
    
        }

        @Override
        public void updateAnimation(){
            vectorSizer.updateLabelInterval(0, space.xAxisMax-space.xAxisMin);
        }
        @Override
        public void traverseDomain(Consumer<double[]> handlePoint){
            traverser.traverseDomain(handlePoint);
        }
        @Override
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
        
        @Override
        public void buildAnimation(String[] parameters) {
            this.function = FunctionsEnum.from(parameters[0]).function;
        }

}
    




