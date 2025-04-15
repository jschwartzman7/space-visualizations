package spacevisuals.animations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

import spacevisuals.enums.FunctionsEnum;
import spacevisuals.enums.VariableEnum;
import spacevisuals.utils.Constants;
import spacevisuals.utils.FunctionBuilder;
/*
 * Abstract class for an animation that applys a function to elements in a space
 */
public abstract class FunctionAnimation extends ColoredAnimation{

    protected Function<double[], double[]> function;

    public FunctionAnimation(){
        this.function = Constants.DEFAULT_FUNCTION;
    }
    public FunctionAnimation(Function<double[], double[]> function){
        if(function == null){
            this.function = Constants.DEFAULT_FUNCTION;
            return;
        }
        this.function = function;
    }

    /**
     * @param functionInput ["5*x" "-y" "2+cos(x)" "x*y*z"]
     * @return VariableEnum ordered list of variables used throughout the function
     */
    public ArrayList<VariableEnum> getFunctionVariables(String[] functionInput){
        ArrayList<VariableEnum> functionVariables = new ArrayList<VariableEnum>();
        for(String singleFunction : functionInput){
            if(singleFunction == null){
                continue;
            }
            if(singleFunction.length() == 0){
                continue;
            }
            String[] singleFunctionStringArray = FunctionBuilder.tokenize(singleFunction);
            if(singleFunctionStringArray == null){
                continue;
            }
            if(singleFunctionStringArray.length == 0){
                continue;
            }
            for(String token: singleFunctionStringArray){
                VariableEnum variable = VariableEnum.from(token);
                if(variable != null){
                    if(!functionVariables.contains(variable)){
                        functionVariables.add(variable);
                    }
                }
            }
        }
        functionVariables.sort(Comparator.comparing(x->x.precedence));
        return functionVariables;
    }

    /*
     * '5*x+y' '2*cos(x)' 'x*y*z' is expected syntax
     */
    @Override
    public void configureAnimation(String[] parameters){
        if(parameters == null){return;}
        if(parameters.length == 0){return;}
        FunctionsEnum presetFunction = FunctionsEnum.from(parameters[0]);
        if(presetFunction != FunctionsEnum.from(null)){
            this.function = presetFunction.function;
            return;
        }
        ArrayList<VariableEnum> functionVariables = getFunctionVariables(parameters);
        Function<double[], double[]> customFunction = FunctionBuilder.parseFunction(parameters, functionVariables);
        if(customFunction != null){
            this.function = customFunction;
            return;
        }
    }
}
