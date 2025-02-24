package spacevisuals.animations.vectorfieldanimations;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.intervalranges.IntervalsRange;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.SpaceFunction2D;
import spacevisuals.functions.functionhandling.FunctionsEnum;
import spacevisuals.animations.PointSetAnimation;

import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField2D extends SpaceFunction2D implements PointSetAnimation {

    private double pointRadius = 0.003;
    private ClippingTraverser traverser;
    private IntervalsRange vectorSizer;
    
    public VectorField2D(){
        super();
        this.traverser = new ClippingTraverser(space, 30);
        this.vectorSizer = new IntervalsRange(new double[]{0.5}, new double[][]{new double[]{10, 23}});
    }
    public VectorField2D(Function<double[], double[]> function){
        super(function);
        this.traverser = new ClippingTraverser(space, 30);
        this.vectorSizer = new IntervalsRange(new double[]{0.5}, new double[][]{new double[]{10, 23}});
    }
    
    @Override
    public void updateAnimation(){
        vectorSizer.updateLabelInterval(0, Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin));
    }
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(handlePoint);
    }
    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        double[] vector = new double[]{output[0]-input[0], output[1]-input[1]};
        double angle = Math.atan2(vector[1], vector[0]);
        StdDraw.filledCircle(input[0], input[1], Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin)*pointRadius);
        StdDraw.setPenColor();
        StdDraw.setPenRadius();
        StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0]);
    }
    @Override
    public void buildAnimation(String[] parameters) {
        this.function = FunctionsEnum.from(parameters[0]).function;
    }
}
    




