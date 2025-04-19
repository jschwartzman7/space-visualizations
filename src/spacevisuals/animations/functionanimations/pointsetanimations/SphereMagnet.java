package spacevisuals.animations.functionanimations.pointsetanimations;

import spacevisuals.animations.PointSetAnimation;
import spacevisuals.spaces.SpaceUser3D;
import spacevisuals.utils.timeintervals.TimeInterval;
import spacevisuals.utils.timeintervals.TimeIntervalBounce;
import edu.princeton.cs.introcs.StdDraw;

public class SphereMagnet extends PointSetAnimation implements SpaceUser3D{

    private double maxPointRadius = 25;
    private int numPoints = 10000;
    private TimeInterval timeInterval;

    public SphereMagnet(){
        super();
        this.timeInterval = new TimeIntervalBounce(.8, 1, 0.005);
        for(int i = 0; i < numPoints; i++){
            double x = Math.random()-0.5;
            double y = Math.random()-0.5;
            double z = Math.random()-0.5;
            double xNorm = x/Math.sqrt(x*x+y*y+z*z);
            double yNorm = y/Math.sqrt(x*x+y*y+z*z);
            double zNorm = z/Math.sqrt(x*x+y*y+z*z);
            points.add(new double[]{xNorm*Math.random()*maxPointRadius, yNorm*Math.random()*maxPointRadius, zNorm*Math.random()*maxPointRadius});
        }
    }

    @Override
    public void updateAnimation(){
        timeInterval.updateT();
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

    @Override
    public void handleInputOutput(double[] input, double[] output) {
        if(Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2)+Math.pow(input[2], 2)) <= Double.MIN_VALUE){
            double[] point = space().toViewScreenPoint(input);
            if(point == null){
                return;
            }
            StdDraw.point(point[0], point[1]);
        }
        else{
            double[] point = space().toViewScreenPoint(transformPoint(new double[]{input[0], input[1], input[2], timeInterval.t}));
            if(point == null){
                return;
            }
            StdDraw.point(point[0], point[1]);
        }
    }
}
