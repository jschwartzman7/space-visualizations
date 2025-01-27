package spacevisuals.animations;

import spacevisuals.spaces.Euclidean2D;
import edu.princeton.cs.introcs.StdDraw;

public class UnitCircle extends BasicAnimation<Euclidean2D> {

    private double maxPointRadius = 70;
    private int numPoints = 250;
    private double[][] points;
    private double tStep = 0.008;
    private double t;

    public UnitCircle(Euclidean2D space, int frameSpeed){
        super(space, frameSpeed);
        this.points = new double[numPoints][2];
        this.t = 0;
        for(int i = 0; i < points.length; i++){
            double theta = 2*Math.PI*Math.random();
            double radius = Math.random()*maxPointRadius;
            double x = radius*Math.cos(theta);
            double y = radius*Math.sin(theta);
            points[i][0] = x;
            points[i][1] = y;
        }
    }

    public void drawAnimation(){
        StdDraw.circle(0, 0, 1);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        for(int i = 0; i < points.length; i++){
            if(Math.sqrt(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2)) <= Double.MIN_VALUE){
                StdDraw.point(points[i][0], points[i][1]);
            }
            else{
                double x = points[i][0]*(1+t*(1/Math.sqrt(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2))-1));
                double y = points[i][1]*(1+t*(1/Math.sqrt(Math.pow(points[i][0], 2)+Math.pow(points[i][1], 2))-1));
                StdDraw.point(x, y);
            }
        }
        StdDraw.setPenColor();
        StdDraw.setPenRadius();

        
    }

    public void updateAnimation(){
        if(t + tStep > 1 || t + tStep < 0){
            tStep = -tStep;
        }
            t += tStep;
    }

    public static void main(String[] args){
        
    }
}
