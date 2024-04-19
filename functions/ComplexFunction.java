import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.awt.event.KeyEvent;


public class ComplexFunction extends abstractFunction{



    /*public class ComplexNumber{
        public double x;
        public double y;
        public double modulus;
        public double arg;
        public double[] value;

        public ComplexNumber(double x, double y){
            this.x = x;
            this.y = y;
            this.modulus = Math.hypot(x, y);
            this.arg = Math.atan2(y, x);
            this.value = new double[]{x, y};
        }

    }*/
    
    public ComplexFunction(){

    }

    public double[] f(double[] input){
        return power(add(multiply(input, input), 1), 0.5);
        //return power(input, 1.0/3);
        //return divide(subtract(exp(multiply(input, new double[]{0, 1})), exp(multiply(input, new double[]{0, -1}))), new double[]{0, 2});


    }

    public double[] zero(){
        return new double[]{0, 0};
    }

    public double[] conjugate(double[] z){
        return new double[]{z[0], -z[1]};
    }

    public double[] add(double[] z, double[] w){
        return new double[]{z[0]+w[0], z[1]+w[1]};
    }

    public double[] add(double[] z, double w){
        return new double[]{z[0]+w, z[1]};
    }

    public double[] subtract(double[] z, double[] w){
        return new double[]{z[0]-w[0], z[1]-w[1]};
    }
    public double[] multiply(double[] z, double[] w){
        return new double[]{z[0]*w[0]-z[1]*w[1], z[0]*w[1]+z[1]*w[0]};
    }
    public double[] divide(double[] z, double[] w){
        if(w[0] != 0 || w[1] != 0){
            double denominator = w[0]*w[0] + w[1]*w[1];
            return new double[]{(z[0]*w[0]+z[1]*w[1])/denominator, (z[1]*w[0]-z[0]*w[1])/denominator};
        }
        System.out.println("divide by 0");
        return null;
        
    }
    
    public double[] exp(double[] z){ // e^z
        return new double[]{Math.exp(z[0])*Math.cos(z[1]), Math.exp(z[0])*Math.sin(z[1])};
    }

    public double[] log(double[] z){ // e^z
        
     
        return new double[]{Math.log(Math.hypot(z[0], z[1])), Math.atan2(z[1], z[0])};
        
    }


    public double[] power(double[] z, double x){ // z^n
        double newMod = Math.pow(Math.hypot(z[0], z[1]), x);
        double newArg = Math.atan2(z[1], z[0])*x % (Math.PI*2);
        return new double[]{newMod*Math.cos(newArg), newMod*Math.sin(newArg)};
    }



    public double[] power(double[] z, double[] w){ // z^w := e^wlogz
        double[] logZ = log(z);
        double[] prod = multiply(w, logZ);
        return exp(prod);
    }

    /*public ComplexNumber raiseTo(ComplexNumber w){
        return new ComplexNumber(Math, y)
    }*/
    public static void main(String[] args) {
        
    }
}
