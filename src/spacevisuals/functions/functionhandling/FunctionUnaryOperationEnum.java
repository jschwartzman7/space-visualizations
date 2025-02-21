package spacevisuals.functions.functionhandling;

import java.util.function.Function;

public enum FunctionUnaryOperationEnum {

    sin((Function<double[], Double> f) -> f.andThen(Math::sin)), 
    cos((Function<double[], Double> f) -> f.andThen(Math::cos)),
    exp((Function<double[], Double> f) -> f.andThen(Math::exp)),;
    
    private Function<Function<double[], Double>, Function<double[], Double>> function;
    
    FunctionUnaryOperationEnum(Function<Function<double[], Double>, Function<double[], Double>> function){
        this.function = function;
    }
    
    public Function<Function<double[], Double>, Function<double[], Double>> getFunction(){
        return function;
    }
    
}

