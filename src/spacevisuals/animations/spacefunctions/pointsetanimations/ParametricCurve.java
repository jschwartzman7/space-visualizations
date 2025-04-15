package spacevisuals.animations.spacefunctions.pointsetanimations;

import java.awt.Color;
import java.util.function.Function;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.utils.timeintervals.TimeInterval;
import spacevisuals.utils.timeintervals.TimeIntervalLoop;

public class ParametricCurve extends PointSetAnimation{

    public static final Function<double[], double[]> DEFAULT_FUNCTION = FunctionsEnum.parametric.function;
    private int maxPoints = 500;
    private int numPoints = 0;
    private TimeInterval timeInterval;

    public ParametricCurve(){
        super(DEFAULT_FUNCTION);
        this.timeInterval = new TimeIntervalLoop(0, Math.PI*2, 0.04);
        this.points = new double[maxPoints][1];
        for(int i = 0; i < points.length; i++){
            points[i][0] = timeInterval.t;
        }
    }
    @Override
    public void updateAnimation(){
        for(int i = 0; i < points.length; i++){
            if(i < numPoints){
                this.points[i][0] = timeInterval.incrementValue(this.points[i][0]);
            }
        }
        this.numPoints++;
    }
    @Override
    public void handlePoint(double[] input) {
        double[] point = function.apply(input);
        if(point.length < 2){
            return;
        }
        StdDraw.setPenColor(colorHelper.getColor(input));
        StdDraw.setPenRadius(0.02);
        StdDraw.point(point[0], point[1]);
    }
}
