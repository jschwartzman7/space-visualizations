package spacevisuals.spaces.axislabelers;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;

public class AxisLabeler3D extends AxisLabeler{

    Euclidean3D space;

    public AxisLabeler3D(Euclidean3D space){
        super(3);
        this.space = space;
    }

    public AxisLabeler3D(Euclidean3D space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(labelIntervals, rangeIntervalRatios);
        this.space = space;
    }

    public void updateLabelIntervals(){
        double xRange = space.xAxisMax-space.xAxisMin;
        updateLabelInterval(0, xRange);
        double yRange = space.yAxisMax-space.yAxisMin;
        updateLabelInterval(1, yRange);
        double zRange = space.zAxisMax-space.zAxisMin;
        updateLabelInterval(2, zRange);
    }
}