package spacevisuals.functions;

public class Rn_Rm {

    public static double[] hype(double[] input){
        return new double[]{0.2*input[1]*input[1]-0.3*input[0]*input[0]};
    }
    
    public static Double[] add(Double[] u, Double[] v){
        Double[] result = new Double[u.length];
        for(int i = 0; i < u.length; i++){
            result[i] = u[i] + v[i];
        }
        return result;
    }
}
