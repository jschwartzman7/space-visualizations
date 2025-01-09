public class FunctionR2_R2 extends abstractFunction{

    /*
    * Function: R^2 -> R^2
    * (x, y) in R^2
    */

    public double[] f(double[] inputVector){
        return new double[]{x(inputVector), y(inputVector)};
    }
    
    public double x(double[] inputVector){
        return (Math.sin(inputVector[0]));
        }

    public double y(double[] inputVector){
        return  (inputVector[1]);
    }
    
    public double[] zero(){
        return new double[]{0, 0};
    }

    public double[] add(double[] u, double[] v){
        return new double[]{u[0]+v[0], u[1]+v[1]};
    }

    public double[] subtract(double[] u, double[] v){
        return new double[]{ u[0]-v[0], u[1]-v[1]};
    }

   
}
