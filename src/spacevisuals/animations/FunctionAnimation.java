package spacevisuals.animations;

import java.util.function.Function;

import spacevisuals.enums.FunctionsEnum;
import spacevisuals.utils.Constants;
import spacevisuals.utils.FunctionBuilder;
import spacevisuals.utils.TraverseAnimation;
/*
 * Abstract class for an animation that applys a function to elements in a space
 */
public abstract class FunctionAnimation extends ColoredAnimation implements TraverseAnimation{

    protected Function<double[], double[]> defaultFunction;
    private Function<double[], double[]> function;

    public FunctionAnimation(){
        super();
        this.defaultFunction = Constants.DEFAULT_FUNCTION;
        this.function = Constants.DEFAULT_FUNCTION;
    }
    public FunctionAnimation(Function<double[], double[]> function){
        super();
        this.defaultFunction = Constants.DEFAULT_FUNCTION;
        if(function == null){
            this.function = Constants.DEFAULT_FUNCTION;
            return;
        }
        this.function = function;
    }

    public double[] applyFunction(double[] input){
        return function.apply(input);
    }

    @Override
    public void drawAnimation(){
        TraverseAnimation.super.drawAnimation();
    }
    
    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if(output == null || output.length == 0){
            return;
        }
        for(double value : output){
            if(Double.isNaN(value) || Double.isInfinite(value)){
                return;
            }
        }
        handleInputOutput(input, function.apply(input));
    }

    public abstract void handleInputOutput(double[] input, double[] output);

    /*
     * '5*x+y' '2*cos(x)' 'x*y*z' is expected syntax
     */
    @Override
    public void configureAnimation(String[] parameters){
        if(parameters == null || parameters.length == 0){
            return;
        }
        try{
            FunctionsEnum presetFunction = FunctionsEnum.valueOf(parameters[0]);
            this.function = presetFunction.function;
        }
        catch(Exception e){
        }
        Function<double[], double[]> customFunction = FunctionBuilder.parseFunction(parameters);
        if(customFunction != null){
            this.function = customFunction;
            return;
        }
        if(this.function == null){
            this.function = defaultFunction;
        }
    }
    
}
