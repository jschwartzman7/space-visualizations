package spacevisuals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.enums.VariableEnum;
import spacevisuals.spaces.AbstractSpace;

public class SpaceFunction<T extends AbstractSpace> extends SpaceUser<T>{

    protected Function<double[], double[]> defaultFunction = FunctionsEnum.identity.function;
    protected Function<double[], double[]> function;
    public ArrayList<VariableEnum> usedVariables = new ArrayList<VariableEnum>();

    public SpaceFunction(T space){
        super(space);
        this.function = defaultFunction;
    }
    public SpaceFunction(T space, Function<double[], double[]> function){
        super(space);
        this.function = function;
    }

    public void fillUsedVariables(String[] functionInput){
        usedVariables.clear();
        for(String singleFunction : functionInput){
            String[] singleFunctionStringArray = FunctionBuilder.tokenize(singleFunction);
            if(singleFunctionStringArray == null){
                continue;
            }
            for(String token: singleFunctionStringArray){
                if(FunctionBuilder.variables.contains(token)){
                    VariableEnum variable = VariableEnum.valueOf(token);
                    if(!usedVariables.contains(variable)){
                        usedVariables.add(variable);
                    }
                }
            }
        }
        usedVariables.sort(Comparator.comparing(x->x.precedence));
    }

    // First attempts to parse custom function, then tries to use function enum, finally defaults to identity.
    /*
     * '5*x+y' '2*cos(x)' 'x*y*z' is expected syntax
     */
    public boolean setCustomFunctionStringArray(String[] functionStringArray){
        if(functionStringArray == null){return false;}
        if(functionStringArray.length == 0){return false;}
        FunctionsEnum presetFunction = FunctionsEnum.from(functionStringArray[0]);
        if(presetFunction != FunctionsEnum.identity){
            this.function = presetFunction.function;
            return true;
        }
        fillUsedVariables(functionStringArray);
        Function<double[], double[]> customFunction = FunctionBuilder.parseFunction(functionStringArray, usedVariables);
        if(customFunction != null){
            this.function = customFunction;
            return true;
        }
        return false;
    }
    public void setFunction(Function<double[], double[]> function){
        this.function = function;
    }
}
