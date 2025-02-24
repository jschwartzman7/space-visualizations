package spacevisuals.animations.pointsetanimations;

import java.awt.Color;
import java.util.LinkedList;
import java.util.function.Consumer;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.SpaceFunction2D;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.functions.functionhandling.FunctionsEnum;
import spacevisuals.helpers.timeintervals.TimeInterval;
import spacevisuals.helpers.timeintervals.TimeIntervalLoop;

public class ParametricCurve extends SpaceFunction2D implements PointSetAnimation{

    private LinkedList<Color> pointColors;
    private double pointRadius = 0.01;
    private int numPoints = 100;
    private double[] points;
    private TimeInterval timeInterval;

    public ParametricCurve(){
        super();
        this.timeInterval = new TimeIntervalLoop(0, Math.PI*2, 0.02);
        this.points = new double[numPoints];
        for(int i = 0; i < points.length; i++){
            double t = Math.random()*Math.PI*2;
            points[i] = t;
        }
    }

    @Override
    public void updateAnimation(){
        for(int i = 0; i < points.length; i++){
            points[i] += timeInterval.t;
        }
        timeInterval.updateT();
    }
    @Override
    public void drawAnimation(){
        PointSetAnimation.super.drawAnimation();
    }
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint) {
        StdDraw.setPenRadius(pointRadius);
        for(int i = 0; i < points.length; i++){
            StdDraw.setPenColor(Color.black);
            handlePoint.accept(new double[]{points[i]});
        }
    }
    @Override
    public void handlePoint(double[] input) {
        double[] point = function.apply(input);
        StdDraw.point(point[0], point[1]);
    }
    @Override
    public void buildAnimation(String[] parameters) {
        this.function = FunctionsEnum.from(parameters[0]).function;
    }
}
