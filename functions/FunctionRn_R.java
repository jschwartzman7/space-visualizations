public class FunctionRn_R extends abstractFunction{

    public double[] zero(){
        return new double[]{0};
    }

    public double dotProduct(double[] u, double[] v){
        double result = 0;
        for(int i = 0; i < u.length; i++){
            result += u[i]*v[i];
        }
        return result;
    }

    public double magnitude(double[] u){
        return Math.sqrt(dotProduct(u, u));
    }

    public double supNorm(double[] u){
        double max = 0;
        for(int i = 0; i < u.length; i++){
            if(Math.abs(u[i]) > max){
                max = Math.abs(u[i]);
            }
        }
        return max;
    }

    public double pNorm(double[] u, double p){
        double sum = 0;
        for(int i = 0; i < u.length; i++){
            sum += Math.pow(Math.abs(u[i]), p);
        }
        return Math.pow(sum, 1/p);
    }
}
