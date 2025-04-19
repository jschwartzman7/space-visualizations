package spacevisuals.animations.functionanimations.spacetraverseranimations.vectorfields;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.utils.IntervalsRange;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;
import spacevisuals.enums.FunctionsEnum;

import java.awt.Color;
import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField2D extends VectorField implements SpaceUser2D{

    private double arrowLengthProportion = 0.1;
    private double arrowAngleDifference = Math.PI / 8;
    
    public VectorField2D(){
        super(new ClippingTraverser());
        initializeVariables();
    }
    public VectorField2D(Function<double[], double[]> function){
        super(function, new ClippingTraverser());
        initializeVariables();
    }
    
    @Override
    public void initializeVariables(){
        this.vectorSizer = new IntervalsRange(1, 1, 15, 32);
        this.colorHelper = new SingleColorStrategy(Color.black);
        this.defaultFunction = FunctionsEnum.identity.function;
    }
    @Override
    public void handleInputOutput(double[] input, double[] output){
        if(output.length < 2){
            return;
        }
        double angle = Math.atan2(output[1], output[0]);
        StdDraw.setPenRadius();
        StdDraw.setPenColor(colorHelper.getColor(input));
        StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0]);
        StdDraw.line(input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0], input[0]+(Math.cos(angle)-Math.cos(angle-arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0], input[1]+(Math.sin(angle)-Math.sin(angle-arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0]);
        StdDraw.line(input[0]+Math.cos(angle)*vectorSizer.labelIntervals[0], input[1]+Math.sin(angle)*vectorSizer.labelIntervals[0], input[0]+(Math.cos(angle)-Math.cos(angle+arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0], input[1]+(Math.sin(angle)-Math.sin(angle+arrowAngleDifference)*arrowLengthProportion)*vectorSizer.labelIntervals[0]);
    }
    @Override
    public double getRange() {
        return Math.min(space().getXRange(), space().getYRange());
    }
}
    




