package spacevisuals.functions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Rn_Rn {

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
    
    public static double[] pairwiseAdd(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x + y);
    }
    public static double[] pairwiseSubtract(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x - y);
    }
    public static double[] pairwiseMultiply(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x * y);
    }
    public static double[] pairwiseDivide(double[] u, double[] v){
        return pairwiseF(u, v, (x, y) -> x / y);
    }

    public static double[] sin(double[] u){
        return elementwiseF(u, (x) -> Math.sin(x));
    }
    public static double[] cos(double[] u){
        return elementwiseF(u, (x) -> Math.cos(x));
    }
    public static double[] power(double[] u, double p){
        return elementwiseF(u, (x) -> Math.pow(x, p));
    }
    public static double[] square(double[] u){
        return power(u, 2);
    }
    public static double[] cube(double[] u){
        return power(u, 3);
    }
    public static double[] sqrt(double[] u){
        return power(u, 0.5);
    }

    public static double[] exp(double[] u){
        return elementwiseF(u, (x) -> Math.exp(x));
    }
    public static double[] exponential(double[] u, double b){
        return elementwiseF(u, (x) -> Math.pow(b, x));
    }
}
