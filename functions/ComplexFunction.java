import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.awt.event.KeyEvent;


public class ComplexFunction extends abstractFunction{

    private double[] one = new double[]{1, 0};
    private double[] i = new double[]{0, 1};
    // z = a + ib


    public double[] f(double[] input){
        //return divide(one, multiply(multiply(input, add(input,one)), add(input, add(one, one))));
        //return zero();
        return exp(divide(one, input));
        //return divide(subtract(exp(multiply(input, new double[]{0, 1})), exp(multiply(input, new double[]{0, -1}))), new double[]{0, 2});
    }

    public double[] zero(){ // 0
        return new double[]{0, 0};
    }

    public double[] identity(double[] z){ // z
        return z;
    }

    public double[] constant(){ // k
        return add(one, i);
    }

    public double[] conjugate(double[] z){ // a - bi
        return new double[]{z[0], -z[1]};
    }

    public double[] add(double[] z, double[] w){ // z + w
        return new double[]{z[0]+w[0], z[1]+w[1]};
    }

    public double[] add(double[] z, double x){ // z + x
        return new double[]{z[0]+x, z[1]};
    }

    public double[] subtract(double[] z, double[] w){ // z - w
        return new double[]{z[0]-w[0], z[1]-w[1]};
    }

    public double[] multiply(double[] z, double[] w){ // z*w
        return new double[]{z[0]*w[0]-z[1]*w[1], z[0]*w[1]+z[1]*w[0]};
    }

    public double[] multiply(double[] z, double x){ // z*x
        return new double[]{z[0]*x, z[1]*x};
    }

    public double[] divide(double[] z, double[] w){ // z / w
        if(w[0] == 0 && w[1] == 0){
            System.out.println("Divide by 0");
            return null;
        }
        double[] numerator = multiply(z, conjugate(w));
        double denominator = w[0]*w[0] + w[1]*w[1];
        return new double[]{numerator[0]/denominator, numerator[1]/denominator};
    }
        
    public double[] divide(double[] z, double x){ // z / x
        if(x == 0){
            System.out.println("Divide by 0");
            return null;
        }
        return new double[]{z[0]/x,z[1]/x};
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

    public double[] power(double[] z, double x){ // z^x := e^logzx
        return exp(multiply(log(z), new double[]{x, 0}));
    }

    public static void main(String[] args) {
        
    }
}
