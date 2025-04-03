package spacevisuals.animations.spacefunctions.vectorfields;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.spacetraversers.PrismTraverser3D;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionStepper;
import spacevisuals.utils.Constants;
import spacevisuals.utils.IntervalsRange;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.functions.*;

import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField3D extends VectorField{

        public VectorField3D(){
            super(new PrismTraverser3D());
            initializeVariables();
        }
        public VectorField3D(Function<double[], double[]> function){
            super(function, new PrismTraverser3D());
            initializeVariables();
        }

        public void initializeVariables(){
            this.colorHelper = new PointMapColorStrategy();
            this.vectorSizer = new IntervalsRange(1, .2, 22, 50);
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
            StdDraw.setPenColor(colorHelper.getColor(input3D));
            double[] p1 = Euclidean3D.Get().toViewScreenPoint(new double[] {input3D[0], input3D[1], input3D[2]});
            double[] p2 = Euclidean3D.Get().toViewScreenPoint(new double[]{input3D[0]+unitVector[0], input3D[1]+unitVector[1], input3D[2]+unitVector[2]});
            if(p1 == null || p2 == null){
                return;
            }
            StdDraw.line(p1[0], p1[1], p2[0], p2[1]);
            //StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*(0.01*range), input[1]+Math.sin(angle)*(0.01*range));
        }
        @Override
        public double getRange() {
            return Math.min(Euclidean3D.Get().getYRange(), Euclidean3D.Get().getXRange());
        }

}
    




