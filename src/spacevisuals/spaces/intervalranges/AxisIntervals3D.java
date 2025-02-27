package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class AxisIntervals3D extends IntervalsRange<Euclidean3D>{

    public AxisIntervals3D(Euclidean3D space){
        super(space);
        this.labelIntervals = new double[]{DEFAULT_INTERVAL, DEFAULT_INTERVAL, DEFAULT_INTERVAL};
        this.rangeIntervalRatios = new double[][]{{DEFAULT_RANGE_INTERVAL_MIN, DEFAULT_RANGE_INTERVAL_MAX}, {DEFAULT_RANGE_INTERVAL_MIN, DEFAULT_RANGE_INTERVAL_MAX}, {DEFAULT_RANGE_INTERVAL_MIN, DEFAULT_RANGE_INTERVAL_MAX}};
    }

    public AxisIntervals3D(Euclidean3D space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(space);
        this.labelIntervals = labelIntervals;
        this.rangeIntervalRatios = rangeIntervalRatios;
    }

    @Override
    public void updateLabelIntervals(){
        double xRange = space.xAxisMax-space.xAxisMin;
        updateLabelInterval(xRange, labelIntervals[0]);
        double yRange = space.yAxisMax-space.yAxisMin;
        updateLabelInterval(yRange, labelIntervals[1]);
        double zRange = space.zAxisMax-space.zAxisMin;
        updateLabelInterval(zRange, labelIntervals[2]);
    }
}