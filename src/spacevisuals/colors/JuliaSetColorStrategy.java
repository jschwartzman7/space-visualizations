package spacevisuals.colors;

import java.awt.Color;

public class JuliaSetColorStrategy implements ColorStrategy {

    public Color getColor(double[] interationsToEscape){
        double iterations = interationsToEscape[0];
        return Color.getHSBColor((float)Math.sin(iterations/(Math.PI*2)), (float)1, (float)1);
    };

}
