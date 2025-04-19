package spacevisuals.animations.functionanimations.pointsetanimations;

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
        for(int i = 0; i < maxPoints; i++){
            points.add(new double[]{timeInterval.t});
        }
    }
    @Override
    public void updateAnimation(){
        for(int i = 0; i < points.size(); i++){
            if(i < numPoints){
                this.points.set(i, new double[]{timeInterval.incrementValue(this.points.get(i)[0])});
            }
        }
        this.numPoints++;
    }
    @Override
    public void handleInputOutput(double[] input, double[] output) {
        if(output.length < 2){
            return;
        }
        StdDraw.setPenColor(colorHelper.getColor(input));
        StdDraw.setPenRadius(0.02);
        StdDraw.point(output[0], output[1]);
    }
}
