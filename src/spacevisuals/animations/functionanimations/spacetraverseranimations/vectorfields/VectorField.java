package spacevisuals.animations.functionanimations.spacetraverseranimations.vectorfields;

import spacevisuals.utils.IntervalsRange;
import spacevisuals.utils.Traverser;
import spacevisuals.animations.SpaceTraverserAnimation;
import java.util.function.*;

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
    




