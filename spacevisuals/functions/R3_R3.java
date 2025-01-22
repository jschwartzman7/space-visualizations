import java.util.function.*;
public class R3_R3{

    /*
    * Function: R^3 -> R^3
    * (x, y, z) in R^3
    */

    static double[] f(double[] inputVector){
        return new double[]{x(inputVector), y(inputVector), z(inputVector)};
    }

    static double x(double[] inputVector){
        return inputVector[0] + inputVector[1];
        }

    static double y(double[] inputVector){
        return 2*inputVector[2];
    }

    static double z(double[] inputVector){
        return Math.pow(inputVector[1], 2);
    }

    static double[] zero(){
        return new double[]{0, 0, 0};
    }
    static double[] one(double[] inputVector){
        return new double[]{1, 0, 0};
    }

    static double[] add(double[] u, double[] v){
        return new double[]{u[0]+v[0], u[1]+v[1], u[2]+v[2]};
    }

    static double[] subtract(double[] u, double[] v){
        return new double[]{u[0]-v[0], u[1]-v[1], u[2]-v[2]};
    }

    static double[] rotatePosXY(double[] inputVector, double rotationRate){
        return Matrix.matrixVectorMultiplication(Matrix.posXY(rotationRate), inputVector);
    }
}
