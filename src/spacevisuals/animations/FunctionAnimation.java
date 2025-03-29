package spacevisuals.animations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;

import spacevisuals.Constants;
import spacevisuals.FunctionBuilder;
import spacevisuals.SpaceUser;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.enums.VariableEnum;
import spacevisuals.spaces.AbstractSpace;
/*
 * Abstract class for an animation that applys a function to elements in a space
 */
public abstract class FunctionAnimation<T extends AbstractSpace> extends SpaceUser<T> implements SpaceAnimation{

    protected final Function<double[], double[]> DEFAULT_FUNCTION = Constants.DEFAULT_FUNCTION;
    protected Function<double[], double[]> function;
    public ArrayList<VariableEnum> functionVariables = new ArrayList<VariableEnum>();

    public FunctionAnimation(T space){
        super(space);
        this.function = DEFAULT_FUNCTION;
    }
    public FunctionAnimation(T space, Function<double[], double[]> function){
        super(space);
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
