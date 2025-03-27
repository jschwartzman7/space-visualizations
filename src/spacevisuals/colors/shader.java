package spacevisuals.colors;

import java.awt.Color;

import spacevisuals.colors.colorstrategies.ColorStrategy;

public class shader implements ColorStrategy{

    ColorStrategy colorStrategy;
    public double correlation;

    public shader(ColorStrategy colorStrategy){ 
        this.colorStrategy = colorStrategy;
    }
    
    @Override
    public Color getColor(double[] input) {
        int red = (int)(colorStrategy.getColor(input).getRed() * ColorStrategy.sigmoid(-this.correlation));
        int green = (int)(colorStrategy.getColor(input).getGreen() * ColorStrategy.sigmoid(-this.correlation));
        int blue = (int)(colorStrategy.getColor(input).getBlue() * ColorStrategy.sigmoid(-this.correlation));
        return new Color(red, green, blue);
    }
    
}
