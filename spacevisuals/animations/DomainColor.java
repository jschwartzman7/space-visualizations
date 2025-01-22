package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashSet;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;

/* Coloring Scheme
    Color gets whiter as radius decreases
    Color gets darker as radius increases
    Hue depends on angle


 */


public class DomainColor extends abstractLatticeAnimation<Euclidean2D> {

    public DomainColor(Function<double[], double[]> function, Euclidean2D space, int pixelResolution, int frameSpeed){
        super(space, frameSpeed, pixelResolution, function);
    }

    private double sigmoid(double x, double a, double k){
        return a/(1+Math.exp(k*x));
    }

    private double getColor(double angle, double magnitude, int colorCode){
        double brightnessWeight = sigmoid(magnitude, 2, .5);
        double damping = Math.exp(-.05*magnitude);
        switch(colorCode){
            case 1:
                return damping*(brightnessWeight*sigmoid(magnitude, 2, .1)+(1-brightnessWeight)*Math.pow(Math.sin(angle/2-Math.PI/6), 4));
            case 2:
                return damping*(brightnessWeight*sigmoid(magnitude, 2, .1)+(1-brightnessWeight)*Math.pow(Math.sin(angle/2+Math.PI/6), 4));
            default:
                return damping*(brightnessWeight*sigmoid(magnitude, 2, .1)+(1-brightnessWeight)*Math.pow(Math.sin(angle/2+Math.PI/2), 4));
        }
    }

    private Color getColor2(double[] w){
        double magnitude = Math.hypot(w[0], w[1]);

        double hue = Math.atan2(w[1], w[0])/(2*Math.PI);
        double saturation = Math.exp(-.1*magnitude);
        double brightness = Math.exp(-.1*magnitude);
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
    }


    public void handleImage(double[] z, double[] w){
        // Brightness variables in [0, 1]
        //
        if(w == null){
            return;
        }
        Color color = getColor2(w);
        //double[] functionValue = ComplexOperations.add(new double[]{x, y}, new double[]{0, 0});
        /*double mappedX = w[0];
        double mappedY = w[1];
        double magnitude = Math.hypot(mappedX, mappedY); // mag = 13
        double angle = Math.atan2(mappedY, mappedX);
        double redStrength = getColor(angle, magnitude, 1);
        double greenStrength = getColor(angle, magnitude, 2);
        double blueStrength = getColor(angle, magnitude, 3);*/
        
      
        //StdDraw.setPenColor((int)(255*redStrength), (int)(255*greenStrength), (int)(255*blueStrength));
        StdDraw.setPenColor(color);
        StdDraw.point(z[0], z[1]);
        //double radius = (space.X_MAX-space.X_MIN)/(2*this.pixelResolution);
        //StdDraw.filledSquare(z[0], z[1], radius);

    }

    public void updateAnimation(){
    }

    public static void main(String[] args) {
        new DomainColor(C_C::exp, new Euclidean2D(5, 10, true), 250, 25).run();
        // f: C -> C
        // each z in C is colored based on the coordinates of f(z)

    }
    
}
