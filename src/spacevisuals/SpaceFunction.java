package spacevisuals;

import java.util.function.Function;

import spacevisuals.functions.functionhandling.FunctionBuilder;
import spacevisuals.functions.functionhandling.FunctionsEnum;
import spacevisuals.spaces.AbstractSpace;

public class SpaceFunction<T extends AbstractSpace> extends SpaceUser<T>{

    public Function<double[], double[]> function;

    public SpaceFunction(){
    }
    public SpaceFunction(Function<double[], double[]> function){
        this.function = function;
    }
    public SpaceFunction(T space){
        super(space);
    }
    public SpaceFunction(T space, Function<double[], double[]> function){
        super(space);
        this.function = function;
    }

    public void setFunctionStringArray(String[] functionStringArray){
        if(functionStringArray == null){
            this.function = FunctionsEnum.identity.getFunction();
            return;
        }
        if(functionStringArray[0].startsWith("<")){
            functionStringArray[functionStringArray.length-1] = functionStringArray[functionStringArray.length-1].substring(0, functionStringArray[functionStringArray.length-1].length()-1);
            this.function = FunctionBuilder.parseFunction(functionStringArray);
            return;
        }
        for(FunctionsEnum function: FunctionsEnum.values()){
            if(functionStringArray[0].equals(function.toString())){
                this.function = function.getFunction();
                return;
            }
        }
        this.function = FunctionsEnum.identity.getFunction();
    }

    public void setFunction(Function<double[], double[]> function){
        this.function = function;
    }
    public Function<double[], double[]> getFunction(){
        return this.function;
    }
}
