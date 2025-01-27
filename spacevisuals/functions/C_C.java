package spacevisuals.functions;

import java.lang.Math;
import java.util.function.Function;

public class C_C{
    /*
     * Function: C -> C, where C ~ R^2 is the complex plane
     * z in C = a + ib, where a and b in R
     */
    public static double[] one = new double[]{1.0, 0.0};
    public static double[] i = new double[]{0.0, 1.0};
    public static double[] zero = new double[]{0.0, 0.0};
    
    public static double[] f(double[] input){
        return exp(multiply(input, input));
    }

    public static double[] zeroFunction(){
        return new double[]{zero[0], zero[1]};
    }

    public static double[] conjugate(double[] z){ // a - bi
        return new double[]{z[0], -z[1]};
    }

    public static double[] add(double[] z, double[] w){ // z + w
        return new double[]{z[0]+w[0], z[1]+w[1]};
    }

    public static double[] subtract(double[] z, double[] w){ // z - w
        return new double[]{z[0]-w[0], z[1]-w[1]};
    }

    public static double[] multiply(double[] z, double[] w){ // z*w = (a+bi)(c+di) = (ac-bd) + (ad+bc)i
        return new double[]{z[0]*w[0]-z[1]*w[1], z[0]*w[1]+z[1]*w[0]};
    }

    public static double[] divide(double[] z, double[] w){ // z / w = (z*conj(w))/(w*conj(w)) = (z*conj(w))/(|w|^2)
        if(w[0] == 0 && w[1] == 0){
            System.out.println("Divide by 0: z/w");
            return new double[]{double.MAX_VALUE, double.MAX_VALUE};
        }
        double[] numerator = multiply(z, conjugate(w));
        double denominator = w[0]*w[0] + w[1]*w[1];
        return new double[]{numerator[0]/denominator, numerator[1]/denominator};
    }
        
    public static double[] exp(double[] z){ // e^z := e^Rez(cosImz + sinImz)
        return new double[]{Math.exp(z[0])*Math.cos(z[1]), Math.exp(z[0])*Math.sin(z[1])};
    }

    public static double[] log(double[] z){ // logz := log|z| + iarg(z)
        return new double[]{Math.log(Math.hypot(z[0], z[1])), Math.atan2(z[1], z[0])};
    }

    public static double[] power(double[] z, double[] w){ // z^w := e^logzw
        return exp(multiply(log(z), w));
    }
}
