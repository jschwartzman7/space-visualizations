package spacevisuals.functionhandling;

import java.util.function.Function;

import spacevisuals.enums.FunctionsEnum;
import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;

public class SpaceFunction<T extends AbstractSpace> extends SpaceUser<T>{


    static final char functionDelimiter = '<';
    static FunctionBuilder functionBuilder = new FunctionBuilder();
    public Function<double[], double[]> function;

    public SpaceFunction(){
    }
    public SpaceFunction(Function<double[], double[]> function){
        this.function = function;
    }

    // First attempts to parse custom function, then tries to use function enum, finally defaults to identity.
    /*
     * '<5*x+y' '2*cos(x)' 'x*y*z>' is expected syntax
     */
    public void setFunctionStringArray(String[] functionStringArray){
        if(functionStringArray.equals(null)){
            this.function = FunctionsEnum.identity.function;
            return;
        }
        for(FunctionsEnum functionEnum : FunctionsEnum.values()){
            if(functionEnum.toString().equals(functionStringArray[0])){
                this.function = functionEnum.function;
                return;
            }
        }
        if(functionStringArray[0].charAt(0) == functionDelimiter){
            functionStringArray[0] = functionStringArray[0].substring(1);
            functionStringArray[functionStringArray.length-1] = functionStringArray[functionStringArray.length-1].substring(0, functionStringArray[functionStringArray.length-1].length()-1);
            Function<double[], double[]> customFunction = functionBuilder.parseFunction(functionStringArray);
            if(customFunction != null){
                this.function = customFunction;
                return;
            }
        }
        this.function = FunctionsEnum.identity.function;
        return;
    }

    public void setFunction(Function<double[], double[]> function){
        this.function = function;
    }
    public Function<double[], double[]> getFunction(){
        return this.function;
    }
}
