package spacevisuals.functions;

public class Rn_Rm {

    public static double hyperbolicParabaloid(double[] input, double p1, double p2){
        return p1*input[0]*input[0]-p2*input[1]*input[1];
    }
    
    public static double[] add(double[] u, double[] v){
        double[] result = new double[u.length];
        for(int i = 0; i < u.length; i++){
            result[i] = u[i] + v[i];
        }
        return result;
    }
    public static double[] subtract(double[] u, double[] v){
        double[] result = new double[u.length];
        for(int i = 0; i < u.length; i++){
            result[i] = u[i] - v[i];
        }
        return result;
    }
}
