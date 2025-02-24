package spacevisuals.functions.functionhandling;

import java.util.function.Function;

public enum UnaryOperationEnum {

    sin((Function<double[], Double> f) -> f.andThen(Math::sin)), 
    cos((Function<double[], Double> f) -> f.andThen(Math::cos)),
    exp((Function<double[], Double> f) -> f.andThen(Math::exp)),;
    
    public final Function<Function<double[], Double>, Function<double[], Double>> function;
    
    UnaryOperationEnum(Function<Function<double[], Double>, Function<double[], Double>> function){
        this.function = function;
    }
    
}

