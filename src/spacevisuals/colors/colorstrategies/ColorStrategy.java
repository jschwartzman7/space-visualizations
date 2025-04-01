package spacevisuals.colors.colorstrategies;

import java.awt.Color;

public interface ColorStrategy {

    public static Color getRandomColor(){
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }
    public static double sigmoid(double x){
        return 1.0/(1+Math.exp(-x));
    }
    
    public Color getColor(double[] input);

}
