public class functionR2_R2 {
    
    public functionR2_R2(){
        
    }

    public double[] f(double[] inputVector){
        return new double[]{x(inputVector), y(inputVector)};
    }

    public double x(double[] inputVector){
        return inputVector[0] + inputVector[1];
        }

    public double y(double[] inputVector){
        return 2*inputVector[2];
    }



    public double[] zero(){
        return new double[]{0, 0};
    }
}
