package spacevisuals.functions;

import java.util.function.Function;

public class Rn_R{

    public static double zero(){
        return 0;
    }

    public static double hyperbolicParabaloid(double[] input, double p1, double p2){
        return p1*input[0]*input[0]-p2*input[1]*input[1];
    }

    public static double dotProduct(double[] u, double[] v){
        double result = 0;
        for(int i = 0; i < u.length; i++){
            result += u[i]*v[i];
        }
        return result;
    }

    public static double magnitude(double[] u){
        return Math.sqrt(dotProduct(u, u));
    }

    private static double sumOf(double[] u, Function<Double, Double> f){
        double result = 0;
        for(int i = 0; i < u.length; i++){
            result += f.apply(u[i]);
        }
        return result;
    }
    private static double productOf(double[] u, Function<Double, Double> f){
        double result = 1;
        for(int i = 0; i < u.length; i++){
            result += f.apply(u[i]);
        }
        return result;
    }

    public static double sumOfVector(double[] u){
        return sumOf(u, x -> x);
    }
    public static double sumOfSquares(double[] u){
        return sumOf(u, x -> x*x);
    }
    public static double sumOfSines(double[] u){
        return sumOf(u, x -> Math.sin(x));
    }
    public static double productOfVector(double[] u){
        return productOf(u, x -> x);
    }
    public static double productOfSines(double[] u){
        return productOf(u, x -> Math.sin(x));
    }

    public static double supNorm(double[] u){
        double max = 0;
        for(int i = 0; i < u.length; i++){
            if(Math.abs(u[i]) > max){
                max = Math.abs(u[i]);
            }
        }
        return max;
    }

    public static double pNorm(double[] u, double p){
        double sum = 0;
        for(int i = 0; i < u.length; i++){
            sum += Math.pow(Math.abs(u[i]), p);
        }
        return Math.pow(sum, 1/p);
    }
}
