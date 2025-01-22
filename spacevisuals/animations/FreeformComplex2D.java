package spacevisuals.animations;

import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;


public class FreeformComplex2D extends BasicAnimation<Euclidean2D> {
    
    final int resolution = 500;

    // improve resolution as zooms in, same with fractals

    Function<double[], double[]> function;
    HashSet<double[]> points;
  
    public FreeformComplex2D(Euclidean2D space, int frameSpeed, Function<double[], double[]> function){
        super(space, frameSpeed);
        this.function =  function;
        this.space = new Euclidean2D(5, 10, true);
        this.points = new HashSet<double[]>();
    }


    public Color getColor2(double[] w){
        double magnitude = Math.hypot(w[0], w[1]);
        double hue = Math.atan2(w[1], w[0])/(2*Math.PI);
        double saturation = Math.exp(-.1*magnitude);
        double brightness = Math.exp(-.1*magnitude);
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
    }

    public void drawAnimation(){
        StdDraw.setPenRadius(0.01);
        for(double[] point : points){
            double[] mappedPoint = function.apply(point);
            StdDraw.setPenColor(getColor2(mappedPoint));
            StdDraw.point(point[0], point[1]);
            StdDraw.point(mappedPoint[0], mappedPoint[1]);
        }
    }

    public void updateAnimation(){
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            points.add(newPoint);
        }
    }


    public static void main(String[] args) {
        new FreeformComplex2D(new Euclidean2D(5, 10, true), 25, C_C::log).run();
    }
}
