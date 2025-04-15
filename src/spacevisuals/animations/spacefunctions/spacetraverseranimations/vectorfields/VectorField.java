package spacevisuals.animations.spacefunctions.spacetraverseranimations.vectorfields;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.spacetraversers.*;
import spacevisuals.spaces.spacetraversers.steppers.ConstantResolutionStepper;
import spacevisuals.utils.Constants;
import spacevisuals.utils.IntervalsRange;
import spacevisuals.utils.Traverser;
import spacevisuals.animations.SpaceTraverserAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.colors.colorstrategies.SingleColorStrategy;

import java.awt.Color;
import java.util.function.*;
import edu.princeton.cs.introcs.StdDraw;

public abstract class VectorField extends SpaceTraverserAnimation {

    protected IntervalsRange vectorSizer;
    protected double arrowLengthProportion = 0.1;
    protected double arrowAngleDifference = Math.PI / 8;
    
    public VectorField(Traverser traverser){
        super(traverser);
        initializeVariables();
    }
    public VectorField(Function<double[], double[]> function, Traverser traverser){
        super(function, traverser);
        initializeVariables();
    }
    
    @Override
    public void updateAnimation(){
        vectorSizer.updateLabelInterval(getRange(), 0);
    }
    
    public abstract  void initializeVariables();
    public abstract double getRange();
}
    




