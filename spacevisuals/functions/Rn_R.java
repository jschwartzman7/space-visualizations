package spacevisuals.functions;

public class Rn_R{

    public static double[] zero(){
        return new double[]{0};
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
