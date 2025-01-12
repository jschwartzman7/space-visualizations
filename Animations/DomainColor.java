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


public class DomainColor extends abstractAnimation{
 

    //final double POINT_SPACING = 0.01;
    //final double POINT_RADIUS = 0.005;
    public Euclidean2D space;
  
    public DomainColor(abstractFunction function, Euclidean2D space, int pixelResolution, int frameSpeed){
        super(function, space, pixelResolution, frameSpeed);
        this.space = space;
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
        double brightness = Math.exp(-.1*magnitude);
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
    }


    public void mapPoint(double[] z){
        // Brightness variables in [0, 1]
        //
        double[] w = function.identity(z);
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
        //StdDraw.filledCircle(x, y, pointRadius);
        double radius = (space.X_MAX-space.X_MIN)/(2*this.pixelResolution);
        StdDraw.filledSquare(z[0], z[1], radius);

    }
/* */
    public void draw(){
        double xStep = (space.X_MAX-space.X_MIN)/this.pixelResolution;
        double yStep = (space.Y_MAX-space.Y_MIN)/this.pixelResolution;
        for(double x = space.X_MIN; x < space.X_MAX+xStep; x += xStep){
            for(double y = space.Y_MIN; y < space.Y_MAX+yStep; y += yStep){
                mapPoint(new double[]{x, y});
            }
        }
    }

    public static void main(String[] args) {
        DomainColor test = new DomainColor(new FunctionC_C(), new Euclidean2D(5, 10, true), 250, 25);
        test.run();
        // f: C -> C
        // each z in C is colored based on the coordinates of f(z)

    }
    
}
