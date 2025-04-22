package spacevisuals.colors.colorstrategies;

import java.awt.Color;

public interface ColorStrategy {

    public static Color getRandomColor(){
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }
    public static double sigmoid(double x){
        return 1.0/(1+Math.exp(-x));
    }
    public static Color juliaSetGetColor(double[] iterationsToEscape, double maxIterations){
        if (iterationsToEscape[0] >= maxIterations) {
            return Color.BLACK;
        }
        double iterations = iterationsToEscape[0];
        return Color.getHSBColor((float)Math.sin(iterations/(Math.PI*4)), (float)1, (float)1);
    }
    public Color getColor(double[] input);

}
