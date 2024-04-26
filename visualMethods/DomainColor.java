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
 

    //final double POINT_SPACING = 0.01;
    //final double POINT_RADIUS = 0.005;
    final int resolution = 500;

    // improve resolution as zooms in, same with fractals

    abstractFunction function;
    euclideanR2 space;
    HashSet<double[]> mappedPoints;
  
    public DomainColor(){
        this.function =  new ComplexFunction();
        this.space = new euclideanR2(2, true, 2);
        this.mappedPoints = new HashSet<double[]>();
    }

    

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

    static Color getColor2(double[] w){
        double magnitude = Math.hypot(w[0], w[1]);

        double hue = Math.atan2(w[1], w[0])/(2*Math.PI);
        double saturation = Math.exp(-.1*magnitude);
        double brightness =1;
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
    }


    public void mapPoint(double[] z){
        // Brightness variables in [0, 1]
        //
        double[] w = function.f(z);
        if(w == null){
            return;
        }
        Color color = getColor2(w);
        //double[] functionValue = ComplexOperations.add(new double[]{x, y}, new double[]{0, 0});
        double mappedX = w[0];
        double mappedY = w[1];
        double magnitude = Math.hypot(mappedX, mappedY); // mag = 13
        double angle = Math.atan2(mappedY, mappedX);
        double redStrength = getColor(angle, magnitude, 1);
        double greenStrength = getColor(angle, magnitude, 2);
        double blueStrength = getColor(angle, magnitude, 3);
        
      
        //StdDraw.setPenColor((int)(255*redStrength), (int)(255*greenStrength), (int)(255*blueStrength));
        StdDraw.setPenColor(color);
        //StdDraw.filledCircle(x, y, pointRadius);
        double radius = (space.X_MAX-space.X_MIN)/(2*resolution);
        StdDraw.filledSquare(z[0], z[1], radius);

    }
/* */
    public void drawPoints(){
        double step = (space.X_MAX-space.X_MIN)/resolution;
        for(double x = space.X_MIN; x < space.X_MAX+step; x += step){
            for(double y = space.Y_MIN; y < space.Y_MAX+step; y += step){
                mapPoint(new double[]{x, y});
            }
        }
    }

    public void run(){
        while(true){
            StdDraw.clear();
            drawPoints();
            space.draw();
            space.update();
            StdDraw.show(50);
        }
    }

    public static void main(String[] args) {
        DomainColor test = new DomainColor();
        test.run();
        // f: C -> C
        // each z in C is colored based on the coordinates of f(z)

    }
    
}
