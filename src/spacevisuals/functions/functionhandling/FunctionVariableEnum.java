package spacevisuals.functions.functionhandling;

public enum FunctionVariableEnum {
    x(0), y(1), z(2), w(3), t(4), u(5), v(6);
    
    public final int precedence;
    
    FunctionVariableEnum(int precedence){
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }
}