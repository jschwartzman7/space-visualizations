package spacevisuals.animations.vectorfieldanimations;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.intervalranges.IntervalsRange;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.spaces.SpaceFunction2D;
import spacevisuals.functions.Rn_R;

import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField2D extends SpaceFunction2D implements PointSetAnimation {

    private double pointRadius = 0.003;
    private ClippingTraverser traverser;
    private IntervalsRange vectorSizer;
    
    public VectorField2D(){
        super();
        this.traverser = new ClippingTraverser(space, 30);
        this.vectorSizer = new IntervalsRange(1, .5, 45, 100);
    }
    public VectorField2D(Function<double[], double[]> function){
        super(function);
        this.traverser = new ClippingTraverser(space, 30);
        this.vectorSizer = new IntervalsRange(1, .5, 45, 100);
    }
    
    @Override
    public void updateAnimation(){
        vectorSizer.updateLabelInterval(Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin), 0);
    }
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(handlePoint);
    }
    @Override
    public void handlePoint(double[] input){
        double[] outputVector = function.apply(input);
        double angle = Math.atan2(outputVector[1], outputVector[0]);
        StdDraw.filledCircle(input[0], input[1], Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin)*pointRadius);
        StdDraw.setPenColor();
        StdDraw.setPenRadius();
        double vectorLength = vectorSizer.labelIntervals[0]/(1+Math.exp(-Rn_R.magnitude(outputVector)));
        StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*vectorLength, input[1]+Math.sin(angle)*vectorLength);
    }
    @Override
    public void buildAnimation(String[] parameters) {
        if(!setCustomFunctionStringArray(parameters)){
            setFunction(FunctionsEnum.from(parameters[0]).function);
        };
    }
}
    




