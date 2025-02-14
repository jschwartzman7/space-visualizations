package spacevisuals.helpers;

import java.awt.Color;

public class ColorHelper {

    public int strategy = 0;
    /*
     * 1: Julia / Mandelbrot Set colorer
     * 2: Domain Color
     */

    public ColorHelper(int strategy){
        this.strategy = strategy;
    }

    public static Color getRandomColor(){
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }

    public static double sigmoid(double x, double shift, double tightness){
        return 1/(1+Math.exp(tightness*(x-shift)));
    }
    public static double sin(double x, double shift, double amplitude){
        return amplitude*Math.sin(x-shift);
    }
    public static double cos(double x, double shift, double amplitude){
        return amplitude*Math.cos(x-shift);
    }
    
    public Color getColor(double[] data){
        Color color;
        switch(strategy){
            case 1:
                color = Color.getHSBColor((float)sigmoid(data[0], data[1], .1), 1,1);
                break;
            case 2:
                double hue = Math.atan2(data[1], data[0])/(2*Math.PI);
                double magnitude = Math.hypot(data[0], data[1]);
                double saturation = Math.exp(-.1*magnitude);
                double brightness = Math.exp(-.1*magnitude);
                color = Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
                break;
            default:
                color = Color.BLACK;
                break;
        }
        return color;
    }
    
}
