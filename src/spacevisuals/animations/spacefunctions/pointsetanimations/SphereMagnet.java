package spacevisuals.animations.spacefunctions.pointsetanimations;

import spacevisuals.animations.PointSetAnimation;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.utils.timeintervals.TimeInterval;
import spacevisuals.utils.timeintervals.TimeIntervalBounce;
import edu.princeton.cs.introcs.StdDraw;

public class SphereMagnet extends PointSetAnimation{

    private double maxPointRadius = 25;
    private int numPoints = 10000;
    private double[][] points;
    private TimeInterval timeInterval;

    public SphereMagnet(){
        super();
        this.timeInterval = new TimeIntervalBounce(.8, 1, 0.005);
        this.points = new double[numPoints][3];
        for(int i = 0; i < points.length; i++){
            double x = Math.random()-0.5;
            double y = Math.random()-0.5;
            double z = Math.random()-0.5;
            double xNorm = x/Math.sqrt(x*x+y*y+z*z);
            double yNorm = y/Math.sqrt(x*x+y*y+z*z);
            double zNorm = z/Math.sqrt(x*x+y*y+z*z);
            points[i][0] = xNorm*Math.random()*maxPointRadius;
            points[i][1] = yNorm*Math.random()*maxPointRadius;
            points[i][2] = zNorm*Math.random()*maxPointRadius;
        }
    }

    @Override
    public void updateAnimation(){
        timeInterval.updateT();
    }
    @Override
    public void handlePoint(double[] input) {
        if(Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2)+Math.pow(input[2], 2)) <= Double.MIN_VALUE){
            double[] point = Euclidean3D.Get().toViewScreenPoint(input);
            if(point == null){
                return;
            }
            StdDraw.point(point[0], point[1]);
        }
        else{
            double[] point = Euclidean3D.Get().toViewScreenPoint(transformPoint(new double[]{input[0], input[1], input[2], timeInterval.t}));
            if(point == null){
                return;
            }
            StdDraw.point(point[0], point[1]);
        }
    }

    public static double[] transformPoint(double[] input){
        double x = input[0];
        double y = input[1];
        double z = input[2];
        double r = Math.sqrt(x*x+y*y+z*z);
        double theta = Math.acos(z/r);
        double phi = Math.atan2(y, x);
        double newR = r*(1+input[3]*(1/r-1));
        return new double[]{newR*Math.sin(theta)*Math.cos(phi), newR*Math.sin(theta)*Math.sin(phi), newR*Math.cos(theta)};

    }
}
