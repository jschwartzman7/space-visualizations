public class R2_R2{

    static double[] f(double[] inputVector){
        return new double[]{x(inputVector), y(inputVector)};
    }

    static double[] test(double[] inputVector){
        return new double[]{2, -2};
    }
    
    static double x(double[] inputVector){
        return (Math.sin(inputVector[0]));
        }

    static double y(double[] inputVector){
        return (Math.cos(inputVector[1]+inputVector[0]));
    }
    
    static double[] zero(){
        return new double[]{0, 0};
    }

    static double[] add(double[] u, double[] v){
        return new double[]{u[0]+v[0], u[1]+v[1]};
    }

    static double[] subtract(double[] u, double[] v){
        return new double[]{ u[0]-v[0], u[1]-v[1]};
    }
}
