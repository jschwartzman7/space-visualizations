package spacevisuals.animations.functionanimations.pointsetanimations;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser4D;
import spacevisuals.utils.ConfigurableAnimation;

public class Sphere4D extends PointSetAnimation implements SpaceUser4D{

    public int numPoints = 1000;
    public double[] center = new double[]{0, 0, 0, 0};
    public double radius = 1;

    public Sphere4D(){
        for(int i = 0; i < numPoints; i++){
            double x = Math.random() - 0.5;
            double y = Math.random() - 0.5;
            double z = Math.random() - 0.5;
            double w = Math.random() - 0.5;
            double magnitude = Math.sqrt(x*x + y*y + z*z + w*w);
            x /= magnitude;
            y /= magnitude;
            z /= magnitude;
            w /= magnitude;
            points.add(new double[]{x, y, z, w});
        }
        
    }
    
    @Override
    public void handleInputOutput(double[] input, double[] output) {
        double[] screenPoint = space().toViewScreenPoint(input);
        StdDraw.point(screenPoint[0], screenPoint[1]);
    }
    
}
