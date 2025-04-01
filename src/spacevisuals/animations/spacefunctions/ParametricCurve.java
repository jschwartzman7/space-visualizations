package spacevisuals.animations.spacefunctions;

import java.awt.Color;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.FunctionAnimation;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.utils.timeintervals.TimeInterval;
import spacevisuals.utils.timeintervals.TimeIntervalLoop;

public class ParametricCurve extends FunctionAnimation implements PointSetAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.parametric.function;
    private double pointRadius = 0.01;
    private int numPoints = 100;
    private double[] points;
    private TimeInterval timeInterval;

    public ParametricCurve(){
        super(DEFAULT_FUNCTION);
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
            points[i] = timeInterval.incrementValue(points[i]);
        }
    }
    @Override
    public void drawAnimation(){
        traverseDomain();
    }
    @Override
    public void traverseDomain() {
        StdDraw.setPenRadius(pointRadius);
        for(int i = 0; i < points.length; i++){
            StdDraw.setPenColor(Color.black);
            handlePoint(new double[]{points[i]});
        }
    }
    @Override
    public void handlePoint(double[] input) {
        double[] point = function.apply(input);
        StdDraw.point(point[0], point[1]);
    }
}
