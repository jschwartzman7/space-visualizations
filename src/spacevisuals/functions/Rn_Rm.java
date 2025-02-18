package spacevisuals.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Rn_Rm {

    private static double[] pairwiseF(double[] u, double[] v, BiFunction<Double, Double, Double> f){
        double[] result = new double[u.length];
        for(int i = 0; i < u.length; i++){
            result[i] = f.apply(u[i], v[i]);
        }
        return result;
    }
    private static double[] elementwiseF(double[] u, Function<Double, Double> f){
        double[] result = new double[u.length];
        for(int i = 0; i < u.length; i++){
            result[i] = f.apply(u[i]);
        }
        return result;
    }
    
    public static double[] add(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x + y);
    }
    public static double[] subtract(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x - y);
    }
    public static double[] multiply(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x * y);
    }
    public static double[] square(double[] u){
        return elementwiseF(u, (x) -> x * x);
    }
    public static double[] sin(double[] u){
        return elementwiseF(u, (x) -> Math.sin(x));
    }
    public static double[] cos(double[] u){
        return elementwiseF(u, (x) -> Math.cos(x));
    }
    public static double[] power(double[] u, int p){
        return elementwiseF(u, (x) -> Math.pow(x, p));
    }
}
