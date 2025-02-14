package spacevisuals.vectorfields;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.helpers.Euclidean2DTraverser;
import spacevisuals.PointSetAnimation;
import spacevisuals.functions.*;

import java.util.HashSet;
import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField2D extends PointSetAnimation<Euclidean2D> {
    
        Euclidean2D space;
        Function<double[], double[]> function;
	    Euclidean2DTraverser traverser;
        private double pointRadius = 0.005;
        private double vectorRadius = 0.002;
        
        public VectorField2D(Euclidean2D space, Function<double[], double[]> function){
            super(space, function);
            this.space = space;
            this.traverser = new Euclidean2DTraverser(space, 20);
        }

        public void handlePoint(double[] input){
            /*
             * Add vector lines to list to send to space to draw
             */
            double[] output = function.apply(input);
            double[] vector = new double[]{output[0]-input[0], output[1]-input[1]};
            double angle = Math.atan2(vector[1], vector[0]);
            //double m = Math.hypot(vector[0], vector[1]);
            //vector[0] /= m;
            //vector[1] /= m;
            double length = Math.min((space.xClipMax-space.xClipMin)/traverser.primaryPixelResolution, (space.yClipMax-space.yClipMin)/traverser.secondaryPixelResolution);
            double range = Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin);

            StdDraw.filledCircle(input[0], input[1], range*pointRadius);
            StdDraw.setPenColor();
            StdDraw.setPenRadius(vectorRadius);
            StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*length, input[1]+Math.sin(angle)*length);
    
        }

        public void traverseDomain(Consumer<double[]> handlePoint){
            traverser.traverseDomain(handlePoint);
        }
        public void updateAnimation(){
        }
    }
    




