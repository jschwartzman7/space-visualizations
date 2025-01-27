package spacevisuals.animations;

import spacevisuals.functions.C_C;
import spacevisuals.spaces.*;
import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.animations.HashSetHelper;


public class FreeformComplex2D extends PointSetAnimation<HashSetHelper>{

    // improve resolution as zooms in, same with fractals

    private HashSet<Double[]> points;
  
    public FreeformComplex2D(Euclidean2D space, int frameRate, Function<Double[], Double[]> function){
        super(space, frameRate, function, new HashSetHelper());
        this.points = traverser.getSet();
    }

    public Color getColor2(Double[] w){
        double magnitude = Math.hypot(w[0], w[1]);
        double hue = Math.atan2(w[1], w[0])/(2*Math.PI);
        double saturation = Math.exp(-.1*magnitude);
        double brightness = Math.exp(-.1*magnitude);
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
        
    }

    public void updateAnimation(){
        if(StdDraw.isMousePressed()){
            Double [] newPoint = new Double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            points.add(newPoint);
        }
        traverser.setSet(points);
    }

    public void handleImage(Double[] input, Double[] output){
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(getColor2(output));
        StdDraw.point(input[0], input[1]);
        StdDraw.point(output[0], output[1]);
    }
}
