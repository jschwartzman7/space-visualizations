package spacevisuals.animations.basicanimations;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.ConfigurableAnimation;
import spacevisuals.spaces.Euclidean4D;

public class Sphere4D implements ConfigurableAnimation{

    public int numPoints = 1000;
    public double[] center = new double[]{0, 0, 0, 0};
    public double radius = 1;
    public double[][] points;

    public Sphere4D(){
        points = new double[numPoints][4];
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
            points[i] = new double[]{x, y, z, w};
        }
        
    }

    @Override
    public void drawAnimation() {
        for(int i = 0; i < numPoints; i++){
            double[] point = points[i];
            double[] screenPoint = Euclidean4D.Get().toViewScreenPoint(point);
            StdDraw.point(screenPoint[0], screenPoint[1]);
        }
    }
    
}
