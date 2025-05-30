package spacevisuals.colors.colorstrategies;

import java.awt.Color;

public class PointMapColorStrategy implements ColorStrategy {

    private double currentHue;
    private double hueSpeed = 0.02;

    public PointMapColorStrategy() {
        this.currentHue = 0;
    }

    @Override
    public Color getColor(double[] input) {
        currentHue = (currentHue+hueSpeed) % 1;
        double hue = currentHue;
        double saturation = 1;
        double brightness = 1;
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
    }

    public void resetHue() {
        this.currentHue = 0;
    }
    
}
