package spacevisuals.animations.spacefunctions.vectorfields;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.SpaceFunction;
import spacevisuals.spaces.spacetraversers.PrismTraverser3D;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.functions.*;
import spacevisuals.helpers.IntervalsRange;

import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField3D extends SpaceFunction<Euclidean3D> implements PointSetAnimation {

	    private SpaceTraverser<Euclidean3D> traverser;
        private IntervalsRange vectorSizer;
    
        public VectorField3D(){
            super(Euclidean3D.Get());
            this.traverser = new PrismTraverser3D(getSpace(), new ConstantResolutionTraverser());
            this.vectorSizer = new IntervalsRange(1, .5, 10, 30);
        }
        public VectorField3D(Function<double[], double[]> function){
            super(Euclidean3D.Get(), function);
            this.traverser = new PrismTraverser3D(getSpace(), new ConstantResolutionTraverser());
            this.vectorSizer = new IntervalsRange(1, .5, 10, 30);
    
        }

        @Override
        public void updateAnimation(){
            vectorSizer.updateLabelInterval(Math.min(getSpace().xAxisMax-getSpace().xAxisMin, getSpace().zAxisMax-getSpace().zAxisMin), 0);
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
            double[] p1 = getSpace().toViewScreenPoint(new double[] {input3D[0], input3D[1], input3D[2]});
            double[] p2 = getSpace().toViewScreenPoint(new double[]{input3D[0]+unitVector[0], input3D[1]+unitVector[1], input3D[2]+unitVector[2]});
            if(p1 == null || p2 == null){
                return;
            }
            StdDraw.line(p1[0], p1[1], p2[0], p2[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }
        
        @Override
        public void configureAnimation(String[] parameters) {
            setCustomFunctionStringArray(parameters);
        }

}
    




