package spacevisuals.animations.spaceuseranimations;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.SpaceUser;
import spacevisuals.animations.SpaceAnimation;

public class Sphere4D extends SpaceUser<Euclidean4D> implements SpaceAnimation{

    public int numPoints = 1000;
    public double[] center = new double[]{0, 0, 0, 0};
    public double radius = 1;
    public double[][] points;

    public Sphere4D(){
        super(Euclidean4D.Get());
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
            double[] screenPoint = getSpace().toViewScreenPoint(point);
            StdDraw.point(screenPoint[0], screenPoint[1]);
        }
    }
    
}
