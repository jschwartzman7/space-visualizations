package spacevisuals.functions.functionhandling;

import java.util.function.BiFunction;

public enum FunctionBinaryOperationEnum {
    add("+", 0, (Double x, Double y) -> x + y),
    subtract("-", 0, (Double x, Double y) -> x - y),
    multiply("*", 1, (Double x, Double y) -> x * y),
    divide("/", 1, (Double x, Double y) -> x / y),
    power("^", 2, (Double x, Double y) -> Math.pow(x, y));
    
    private String symbol;
    private int precedence;
    private BiFunction<Double, Double, Double> function;
    
    private FunctionBinaryOperationEnum(String symbol, int precedence, BiFunction<Double, Double, Double> function){
        this.symbol = symbol;
        this.precedence = precedence;
        this.function = function;
    }
    public String getSymbol(){
        return symbol;
    }
    public int getPrecedence(){
        return precedence;
    }
    public BiFunction<Double, Double, Double> getFunction(){
        return function;
    }
}
