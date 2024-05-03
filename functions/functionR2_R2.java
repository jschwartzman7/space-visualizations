public class functionR2_R2 extends abstractFunction{
    
    public functionR2_R2(){
        
    }

    public double[] f(double[] inputVector){
        return new double[]{x(inputVector), y(inputVector)};
    }

    public double x(double[] inputVector){
        return (inputVector[0]+inputVector[1]);
        }

    public double y(double[] inputVector){
        return  (inputVector[0]*inputVector[1]);
    }

    public double[] identity(double[] inputVector){
        return inputVector;
    }

    public double[] constant(){
        return new double[]{1, 1};
    }

    public double[] subtract(double[] u, double[] v){
        assert(u.length == v.length);
        double x = u[0]-v[0];
        double y = u[1]-v[1];
        return new double[]{x, y};
    }

    public double[] zero(){
        return new double[]{0, 0};
    }
}
