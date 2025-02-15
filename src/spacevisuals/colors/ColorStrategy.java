package spacevisuals.colors;

import java.awt.Color;

public interface ColorStrategy {

    public static Color getRandomColor(){
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
    }
    
    public Color getColor(double[] input);

}
