package spacevisuals.enums;

public enum VariableEnum {
    x(0), y(1), z(2), w(3), t(4), u(5), v(6);
    
    public final int precedence;
    
    VariableEnum(int precedence){
        this.precedence = precedence;
    }

    public static VariableEnum fromPrecedence(int precedence){
        for(VariableEnum variable : VariableEnum.values()){
            if(variable.precedence == precedence){
                return variable;
            }
        }
        return null;
    }
}