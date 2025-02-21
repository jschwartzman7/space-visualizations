package spacevisuals.functions.functionhandling;

public enum FunctionConstantEnum {
    pi(Math.PI),
    e(Math.E);
    
    private double value;
    
    private FunctionConstantEnum(double value){
        this.value = value;
    }
    
    public double getValue(){
        return value;
    }
    
}
