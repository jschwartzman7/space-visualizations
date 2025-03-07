package spacevisuals;

import java.lang.reflect.AnnotatedTypeVariable;
import java.util.function.Function;
import spacevisuals.FunctionBuilder;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;

public class SpaceFunction<T extends AbstractSpace> extends SpaceUser<T>{

    private static FunctionBuilder functionBuilder = new FunctionBuilder();
    protected Function<double[], double[]> defaultFunction = FunctionsEnum.identity.function;
    protected Function<double[], double[]> function;

    public SpaceFunction(T space){
        super(space);
        this.function = defaultFunction;
    }
    public SpaceFunction(T space, Function<double[], double[]> function){
        super(space);
        this.function = function;
    }

    // First attempts to parse custom function, then tries to use function enum, finally defaults to identity.
    /*
     * '5*x+y' '2*cos(x)' 'x*y*z' is expected syntax
     */
    public boolean setCustomFunctionStringArray(String[] functionStringArray){
        if(functionStringArray == null){return false;}
        if(functionStringArray.length == 0){return false;}
        Function<double[], double[]> customFunction = functionBuilder.parseFunction(functionStringArray);
        if(customFunction != null){
            this.function = customFunction;
            return true;
        }
        FunctionsEnum presetFunction = FunctionsEnum.from(functionStringArray[0]);
        if(presetFunction != FunctionsEnum.identity){
            this.function = presetFunction.function;
            return true;
        }
        return false;
    }
    public void setFunction(Function<double[], double[]> function){
        this.function = function;
    }
}
