package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.helpers.Lattice2DHelper2D;

public class DomainColor extends PointSetAnimation<Euclidean2D>{

    Lattice2DHelper2D traverser;

    public DomainColor(Euclidean2D space, int frameRate, Function<double[], double[]> function, int pixelResolution){
        super(space, frameRate, function);
        this.traverser = new Lattice2DHelper2D (space, pixelResolution);
    }

    private double sigmoid(double x, double a, double k){
        return a/(1+Math.exp(k*x));
    }

    private double getColor(double angle, double magnitude, int colorCode){
        double brightnessWeight = sigmoid(magnitude, 2, .5);
        double damping = Math.exp(-.05*magnitude);
        switch(colorCode){
            case 1: return damping*(brightnessWeight*sigmoid(magnitude, 2, .1)+(1-brightnessWeight)*Math.pow(Math.sin(angle/2-Math.PI/6), 4));
            case 2: return damping*(brightnessWeight*sigmoid(magnitude, 2, .1)+(1-brightnessWeight)*Math.pow(Math.sin(angle/2+Math.PI/6), 4));
            default: return damping*(brightnessWeight*sigmoid(magnitude, 2, .1)+(1-brightnessWeight)*Math.pow(Math.sin(angle/2+Math.PI/2), 4));
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
        if(w == null){
            return;
        }
        Color color = getColor2(w);
        double[] z2 = new double[]{z[0], z[1]};
        traverser.drawPointRectangle(z2, color);
        //double radius = (space.X_MAX-space.X_MIN)/(2*this.pixelResolution);
        //StdDraw.filledSquare(z[0], z[1], radius);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        traverser.traverseDomain(this::handlePoint);
    }
}
