package spacevisuals.animations.spacefunctions.vectorfields;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.SpaceFunction;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionTraverser;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.functions.Rn_R;

import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public class VectorField2D extends SpaceFunction<Euclidean2D> implements PointSetAnimation {

    private double pointRadius = 0.003;
    private double vectorLengthProportion = 0.05;
    private ClippingTraverser traverser;
    
    public VectorField2D(){
        super(Euclidean2D.Get());
        this.defaultFunction = FunctionsEnum.sin.function;
        this.traverser = new ClippingTraverser(getSpace(), new ConstantResolutionTraverser());
    }
    public VectorField2D(Function<double[], double[]> function){
        super(Euclidean2D.Get(), function);
        this.traverser = new ClippingTraverser(getSpace(), new ConstantResolutionTraverser());
    }
    
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(handlePoint);
    }
    @Override
    public void handlePoint(double[] input){
        double[] outputVector = function.apply(input);
        double angle = Math.atan2(outputVector[1], outputVector[0]);
        StdDraw.filledCircle(input[0], input[1], Math.min(getSpace().xClipMax-getSpace().xClipMin, getSpace().yClipMax-getSpace().yClipMin)*pointRadius);
        StdDraw.setPenColor();
        StdDraw.setPenRadius();
        
        double vectorLength = (Math.min(getSpace().xClipMax-getSpace().xClipMin, getSpace().yClipMax-getSpace().yClipMin)*vectorLengthProportion)/(1+Math.exp(-Rn_R.magnitude(outputVector)));
        StdDraw.line(input[0], input[1], input[0]+Math.cos(angle)*vectorLength, input[1]+Math.sin(angle)*vectorLength);
    }
    @Override
    public void configureAnimation(String[] parameters) {
        setCustomFunctionStringArray(parameters);
    }
}
    




