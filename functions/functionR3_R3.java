public class FunctionR3_R3 extends abstractFunction{

    /*
    * Function: R^3 -> R^3
    * (x, y, z) in R^3
    */

    public double[] f(double[] inputVector){
        return new double[]{x(inputVector), y(inputVector), z(inputVector)};
    }

    public double x(double[] inputVector){
        return inputVector[0] + inputVector[1];
        }

    public double y(double[] inputVector){
        return 2*inputVector[2];
    }

    public double z(double[] inputVector){
        return Math.pow(inputVector[1], 2);
    }

    public double[] zero(){
        return new double[]{0, 0, 0};
    }

    public double[] add(double[] u, double[] v){
        return new double[]{u[0]+v[0], u[1]+v[1], u[2]+v[2]};
    }

    public double[] subtract(double[] u, double[] v){
        return new double[]{u[0]-v[0], u[1]-v[1], u[2]-v[2]};
    }
}
