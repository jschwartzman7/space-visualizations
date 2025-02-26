package spacevisuals.colorstrategies;


import java.awt.Color;
import spacevisuals.functions.Rn_R;

public class DomainColorStrategy implements ColorStrategy {

    public Color getColor(double[] z){
        double zMag = Rn_R.magnitude(z);
        double hue = (Math.atan2(z[1], z[0])+Math.PI)/(2*Math.PI);
        double saturation = 1;
        double brightness = 1/(1+zMag);
        return Color.getHSBColor((float)hue, (float)saturation, (float)brightness);
    };

}
