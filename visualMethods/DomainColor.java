import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashSet;
import java.awt.Color;
import java.awt.event.KeyEvent;

/* Coloring Scheme
    Color gets whiter as radius decreases
    Color gets darker as radius increases
    Hue depends on angle


 */


public class DomainColor extends abstractVisualMethod{
 


    static final double resolution = 0.3;
    static final double pointRadius = 0.15;
  
    abstractFunction function = new ComplexFunction();
    euclideanR2 space = new euclideanR2(5, false, 0);
    HashSet<double[]> mappedPoints;

    static double sigmoid(double x, double a, double k){
        return a/(1+Math.exp(k*x));
    }

    static double getColor(double angle, double magnitude, int colorCode){
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

    void mapPoint(double[] z){
        // Brightness variables in [0, 1]
        //
        double[] w = function.f(z);
        //double[] functionValue = ComplexOperations.add(new double[]{x, y}, new double[]{0, 0});
        double mappedX = w[0];
        double mappedY = w[1];
        double magnitude = Math.hypot(mappedX, mappedY); // mag = 13
        double angle = Math.atan2(mappedY, mappedX);
        double redStrength = getColor(angle, magnitude, 1);
        double greenStrength = getColor(angle, magnitude, 2);
        double blueStrength = getColor(angle, magnitude, 3);
        
      
        StdDraw.setPenColor((int)(255*redStrength), (int)(255*greenStrength), (int)(255*blueStrength));
        //StdDraw.filledCircle(x, y, pointRadius);
        StdDraw.filledSquare(z[0], z[1], pointRadius);

    }
/* */
    public void addFunctionPoints(){
        for(double x = space.X_MIN; x <= space.X_MAX+resolution; x += resolution){
            for(double y = space.Y_MIN; y <= space.Y_MAX+resolution; y += resolution){
                mapPoint(new double[]{x, y});
            }
        }
    }

    public void run(){
        StdDraw.setScale(-space.defaultRange, space.defaultRange);
        StdDraw.enableDoubleBuffering();
        while(true){
            StdDraw.clear();
            space.draw();
            addFunctionPoints();
            space.update();
            StdDraw.show(30);
        }
    }

    public static void main(String[] args) {
        DomainColor test = new DomainColor();
        test.run();
        // f: C -> C
        // each z in C is colored based on the coordinates of f(z)

    }
    
}
