package spacevisuals.enums;

public enum MathConstantEnum {
    pi(Math.PI),
    e(Math.E);
    
    public final double value;
    
    private MathConstantEnum(double value){
        this.value = value;
    }
}
