import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.awt.event.KeyEvent;


public class FunctionC_C extends abstractFunction{

    /*
     * Function: C -> C, where C = R^2 is the complex plane
     * z in C = a + ib, where a and b in R
     */

    public double[] one = new double[]{1, 0};
    public double[] i = new double[]{0, 1};
    public double[] zero = new double[]{0, 0};
    // z = a + ib


    public double[] f(double[] input){
        //return divide(one, multiply(multiply(input, add(input,one)), add(input, add(one, one))));
        //return zero();
        //return divide(one, subtract(input, one));
        return exp(multiply(input, input));
        //return divide(subtract(exp(multiply(input, new double[]{0, 1})), exp(multiply(input, new double[]{0, -1}))), new double[]{0, 2});
    }

    public double[] identity(double[] z){
        return z;
    }

    public double[] zero(){
        return zero;
    }

    public double[] conjugate(double[] z){ // a - bi
        return new double[]{z[0], -z[1]};
    }

    public static double[] add(double[] z, double[] w){ // z + w
        return new double[]{z[0]+w[0], z[1]+w[1]};
    }

    public double[] subtract(double[] z, double[] w){ // z - w
        return new double[]{z[0]-w[0], z[1]-w[1]};
    }

    public static double[] multiply(double[] z, double[] w){ // z*w = (a+bi)(c+di) = (ac-bd) + (ad+bc)i
        return new double[]{z[0]*w[0]-z[1]*w[1], z[0]*w[1]+z[1]*w[0]};
    }

    public double[] divide(double[] z, double[] w){ // z / w = (z*conj(w))/(w*conj(w)) = (z*conj(w))/(|w|^2)
        if(w[0] == 0 && w[1] == 0){
            System.out.println("Divide by 0: z/w");
            return new double[]{Double.MAX_VALUE, Double.MAX_VALUE};
        }
        double[] numerator = multiply(z, conjugate(w));
        double denominator = w[0]*w[0] + w[1]*w[1];
        return new double[]{numerator[0]/denominator, numerator[1]/denominator};
    }
        
    public double[] exp(double[] z){ // e^z := e^Rez(cosImz + sinImz)
        return new double[]{Math.exp(z[0])*Math.cos(z[1]), Math.exp(z[0])*Math.sin(z[1])};
    }

    public double[] log(double[] z){ // logz := log|z| + iarg(z)
        return new double[]{Math.log(Math.hypot(z[0], z[1])), Math.atan2(z[1], z[0])};
        
    }

    public double[] power(double[] z, double[] w){ // z^w := e^logzw
        return exp(multiply(log(z), w));
    }

    public static void main(String[] args) {
        
    }
}
