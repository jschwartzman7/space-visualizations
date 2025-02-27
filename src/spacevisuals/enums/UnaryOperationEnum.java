package spacevisuals.enums;

import java.util.function.Function;

public enum UnaryOperationEnum {

    sin((Function<double[], Double> f) -> f.andThen(Math::sin)), 
    cos((Function<double[], Double> f) -> f.andThen(Math::cos));
    
    public final Function<Function<double[], Double>, Function<double[], Double>> function;
    
    UnaryOperationEnum(Function<Function<double[], Double>, Function<double[], Double>> function){
        this.function = function;
    }
    
}

