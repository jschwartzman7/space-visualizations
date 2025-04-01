package spacevisuals.colors;

import java.awt.Color;

import spacevisuals.animations.polygons.solids.Triangle;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.utils.Camera3D;
import spacevisuals.functions.Matrix3D;
import spacevisuals.functions.Rn_R;
import spacevisuals.functions.Rn_Rn;

public class Shader implements ColorStrategy{

    private ColorStrategy colorStrategy;
    private Camera3D lightSource;

    public Shader(ColorStrategy colorStrategy){ 
        this.colorStrategy = colorStrategy;
        this.lightSource = new Camera3D(Math.PI,0,0,0,0,-30,10);
    }
    
    @Override
    public Color getColor(double[] input) {
        return colorStrategy.getColor(input);
    }
    public Color getColor(Triangle triangle) {
        double dotProduct = Rn_R.dotProduct(lightSource.toCameraPosition(new double[]{1,1,1}), Matrix3D.crossProduct(Rn_Rn.pairwiseSubtract(triangle.shape[1], triangle.shape[0]), Rn_Rn.pairwiseSubtract(triangle.shape[2], triangle.shape[0])));
        int red = (int)(colorStrategy.getColor(triangle.shape[0]).getRed() * ColorStrategy.sigmoid(-dotProduct));
        int green = (int)(colorStrategy.getColor(triangle.shape[0]).getGreen() * ColorStrategy.sigmoid(-dotProduct));
        int blue = (int)(colorStrategy.getColor(triangle.shape[0]).getBlue() * ColorStrategy.sigmoid(-dotProduct));
        return new Color(red, green, blue);
    }
    
}
