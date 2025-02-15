package spacevisuals.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Rn_Rm {

    private static double[] pairwiseFunction(double[] u, double[] v, BiFunction<Double, Double, Double> f){
        double[] result = new double[u.length];
        for(int i = 0; i < u.length; i++){
            result[i] = f.apply(u[i], v[i]);
        }
        return result;
    }
    
    public static double[] add(double[] u, double[] v){
        return pairwiseFunction(u, v, (x, y) -> x + y);
    }
    public static double[] subtract(double[] u, double[] v){
        return pairwiseFunction(u, v, (x, y) -> x - y);
    }
    public static double[] multiply(double[] u, double[] v){
        return pairwiseFunction(u, v, (x, y) -> x * y);
    }
    public static double[] sin(double[] u){
        return pairwiseFunction(u, u, (x, y) -> Math.sin(x));
    }
}
