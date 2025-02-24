package spacevisuals.functions.functionhandling;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BiFunction;

public enum BinaryOperationEnum {
    add("+", 0, (Double x, Double y) -> x + y),
    subtract("-", 0, (Double x, Double y) -> x - y),
    multiply("*", 1, (Double x, Double y) -> x * y),
    divide("/", 1, (Double x, Double y) -> x / y),
    power("^", 2, (Double x, Double y) -> Math.pow(x, y));
    
    public final String symbol;
    public final int precedence;
    public final BiFunction<Double, Double, Double> function;
    
    private BinaryOperationEnum(String symbol, int precedence, BiFunction<Double, Double, Double> function){
        this.symbol = symbol;
        this.precedence = precedence;
        this.function = function;
    }

    public String getSymbol() {
        return symbol;
    }
}
