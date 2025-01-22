import java.util.function.*;

public class R2_R{

    static double[] zero(){
        return new double[]{0};
    }

    static double[] hype(double[] input){
        return new double[]{0.2*input[1]*input[1]-0.3*input[0]*input[0]};
    }
    static Function<double[], double[]> hype2 = (input) -> new double[]{0.2*input[1]*input[1]-0.3*input[0]*input[0]};
}
