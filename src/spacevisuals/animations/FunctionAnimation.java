package spacevisuals.animations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

import spacevisuals.FunctionBuilder;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.enums.VariableEnum;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;
import spacevisuals.utils.Constants;
/*
 * Abstract class for an animation that applys a function to elements in a space
 */
public abstract class FunctionAnimation implements SpaceAnimation{

    protected final Function<double[], double[]> DEFAULT_FUNCTION = Constants.DEFAULT_FUNCTION1D;
    protected Function<double[], double[]> function;
    public ArrayList<VariableEnum> functionVariables = new ArrayList<VariableEnum>();

    public FunctionAnimation(){
        this.function = DEFAULT_FUNCTION;
    }
    public FunctionAnimation(Function<double[], double[]> function){
        this.function = function;
    }

    public void fillFunctionVariables(String[] functionInput){
        functionVariables.clear();
        for(String singleFunction : functionInput){
            String[] singleFunctionStringArray = FunctionBuilder.tokenize(singleFunction);
            if(singleFunctionStringArray == null){
                continue;
            }
            for(String token: singleFunctionStringArray){
                if(FunctionBuilder.variables.contains(token)){
                    VariableEnum variable = VariableEnum.valueOf(token);
                    if(!functionVariables.contains(variable)){
                        functionVariables.add(variable);
                    }
                }
            }
        }
        functionVariables.sort(Comparator.comparing(x->x.precedence));
    }

    // First attempts to parse custom function, then tries to use function enum, finally defaults to identity.
    /*
     * '5*x+y' '2*cos(x)' 'x*y*z' is expected syntax
     */
    @Override
    public void configureAnimation(String[] parameters){
        if(parameters == null){return;}
        if(parameters.length == 0){return;}
        FunctionsEnum presetFunction = FunctionsEnum.from(parameters[0]);
        if(presetFunction != FunctionsEnum.identity){
            this.function = presetFunction.function;
            return;
        }
        fillFunctionVariables(parameters);
        Function<double[], double[]> customFunction = FunctionBuilder.parseFunction(parameters, functionVariables);
        if(customFunction != null){
            this.function = customFunction;
            return;
        }
    }
}
