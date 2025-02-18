package spacevisuals.pointsetanimations;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.helpers.*;

import java.util.function.Consumer;

import spacevisuals.*;
import edu.princeton.cs.introcs.StdDraw;

public class SphereMagnet extends Animation3DSpace implements PointSetAnimation{

    private double maxPointRadius = 70;
    private int numPoints = 25000;
    private double[][] points;
    private TimeInterval timeInterval;
    Euclidean3D space;

    public SphereMagnet(Euclidean3D space){
        super(space, SphereMagnet::transformPoint);
        this.space = space;
        this.timeInterval = new TimeInterval(.5, 1, 0.004);
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

    public void updateAnimation(){
        timeInterval.updateT();
    }

    /*public static double[] transformPoint(double t, Double[] input){
        return new double[]{input[0]*(1+t*(1/Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2))-1)), input[1]*(1+t*(1/Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2))-1))};
    }*/
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

    public void handlePoint(double[] input) {
        if(Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2)+Math.pow(input[2], 2)) <= Double.MIN_VALUE){
            double[] point = space.toViewScreenPoint(input);
            StdDraw.point(point[0], point[1]);
        }
        else{
            double[] point = space.toViewScreenPoint(transformPoint(new double[]{input[0], input[1], input[2], timeInterval.t}));
            StdDraw.point(point[0], point[1]);
        }
    }
    
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint) {
        for(int i = 0; i < points.length; i++){
            handlePoint.accept(points[i]);
        }
    }
    @Override
    public void drawAnimation(){
        PointSetAnimation.super.drawAnimation();
    }
}
