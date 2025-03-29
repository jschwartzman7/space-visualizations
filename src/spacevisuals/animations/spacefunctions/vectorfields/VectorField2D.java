package spacevisuals.animations.spacefunctions.vectorfields;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import spacevisuals.Constants;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.helpers.IntervalsRange;

import java.awt.Color;
import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField2D extends SpaceTraverserAnimation<Euclidean2D> {

    private IntervalsRange vectorSizer;
    private ColorStrategy colorHelper;
    private double arrowLengthProportion = 0.1;
    private double arrowAngleDifference = Math.PI / 8;
    
    public VectorField2D(){
        super(Euclidean2D.Get(), new ClippingTraverser(Euclidean2D.Get(), new ConstantResolutionTraverser(), Constants.PIXEL_RESOLUTION_LOW));
        this.vectorSizer = new IntervalsRange(1, 1, 15, 32);
        this.colorHelper = new SingleColorStrategy(Color.white);
    }
    public VectorField2D(Function<double[], double[]> function){
        super(Euclidean2D.Get(), function, new ClippingTraverser(Euclidean2D.Get(), new ConstantResolutionTraverser(), Constants.PIXEL_RESOLUTION_LOW));
        this.vectorSizer = new IntervalsRange(1, 1, 15, 32);
        this.colorHelper = new SingleColorStrategy(Color.white);
    }
    
    @Override
    public void updateAnimation(){
        vectorSizer.updateLabelInterval((Math.min(getSpace().getXRange(), getSpace().getYRange())), 0);
    }
    
    @Override
    public void handlePoint(double[] input){
        double[] outputVector = this.function.apply(input);
        double angle = Math.atan2(outputVector[1], outputVector[0]);
        StdDraw.setPenRadius();
        StdDraw.setPenColor(colorHelper.getColor(input));
        StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0]);
        StdDraw.line(input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0], input[0]+(Math.cos(angle)-Math.cos(angle-arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0], input[1]+(Math.sin(angle)-Math.sin(angle-arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0]);
        StdDraw.line(input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0], input[0]+(Math.cos(angle)-Math.cos(angle+arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0], input[1]+(Math.sin(angle)-Math.sin(angle+arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0]);
        
    }
}
    




