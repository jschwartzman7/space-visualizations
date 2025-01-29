package spacevisuals.animations;

import spacevisuals.spaces.Euclidean2D;

import java.util.function.Consumer;

import edu.princeton.cs.introcs.StdDraw;

public class UnitCircle extends PointSetAnimation<Euclidean2D> {

    private double maxPointRadius = 70;
    private int numPoints = 250000;
    private double[][] points;
    private TimeInterval T;

    public UnitCircle(Euclidean2D space, int frameSpeed){
        super(space, frameSpeed, null);
        this.T = new TimeInterval(0, 1, 0.008);
        this.points = new double[numPoints][2];
        for(int i = 0; i < points.length; i++){
            double theta = 2*Math.PI*Math.random();
            double radius = Math.random()*maxPointRadius;
            double x = radius*Math.cos(theta);
            double y = radius*Math.sin(theta);
            points[i][0] = x;
            points[i][1] = y;
        }
    }

    public void updateAnimation(){
        T.updateT();
    }

    public static double[] transformPoint(double t, Double[] input){
        return new double[]{input[0]*(1+t*(1/Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2))-1)), input[1]*(1+t*(1/Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2))-1))};
    }

    public void handleImage(Double[] input, Double[] output){}

    public void handlePoint(Double[] input) {
        if(Math.sqrt(Math.pow(input[0], 2)+Math.pow(input[1], 2)) <= Double.MIN_VALUE){
            StdDraw.point(input[0], input[1]);
        }
        else{
            double[] point = space.toDrawablePoint(transformPoint(T.t, input));
            StdDraw.point(point[0], point[1]);
        }
    }

    @Override
    public void traverseDomain(Consumer<Double[]> handlePoint) {
        for(int i = 0; i < points.length; i++){
            handlePoint.accept(new Double[]{points[i][0], points[i][1]});
        }
    }
}
