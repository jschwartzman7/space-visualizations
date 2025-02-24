package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.Euclidean3D;

public class AxisIntervals3D extends IntervalsRange<Euclidean3D>{

    public AxisIntervals3D(Euclidean3D space){
        super(3);
        setSpace(space);
    }

    public AxisIntervals3D(Euclidean3D space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(labelIntervals, rangeIntervalRatios);
        setSpace(space);
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