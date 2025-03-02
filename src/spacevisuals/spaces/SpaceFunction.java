package spacevisuals.spaces;

import java.util.function.Function;

import spacevisuals.enums.FunctionsEnum;
import spacevisuals.FunctionBuilder;

public class SpaceFunction<T extends AbstractSpace> extends SpaceUser<T>{

    static FunctionBuilder functionBuilder = new FunctionBuilder();
    public Function<double[], double[]> function;

    public SpaceFunction(){
    }
    public SpaceFunction(Function<double[], double[]> function){
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
        return false;
    }

    public void setFunction(Function<double[], double[]> function){
        this.function = function;
    }
    public Function<double[], double[]> getFunction(){
        return this.function;
    }
}
