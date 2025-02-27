package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.SpaceUser;

public class AxisIntervals2D extends IntervalsRange<Euclidean2D>{

    public AxisIntervals2D(Euclidean2D space){
        super(space);
        this.labelIntervals = new double[]{DEFAULT_INTERVAL, DEFAULT_INTERVAL};
        this.rangeIntervalRatios = new double[][]{{DEFAULT_RANGE_INTERVAL_MIN, DEFAULT_RANGE_INTERVAL_MAX}, {DEFAULT_RANGE_INTERVAL_MIN, DEFAULT_RANGE_INTERVAL_MAX}};
    }

    public AxisIntervals2D(Euclidean2D space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(space);
        this.labelIntervals = labelIntervals;
        this.rangeIntervalRatios = rangeIntervalRatios;
    }

    @Override
    public void updateLabelIntervals(){
        double xClipRange = space.xClipMax-space.xClipMin;
        labelIntervals[0] = updateLabelInterval(xClipRange, labelIntervals[0]);
        double yClipRange = space.yClipMax-space.yClipMin;
        labelIntervals[1] = updateLabelInterval(yClipRange, labelIntervals[1]);
    }
}