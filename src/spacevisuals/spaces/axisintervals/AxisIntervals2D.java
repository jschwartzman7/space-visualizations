package spacevisuals.spaces.axisintervals;

import spacevisuals.spaces.Euclidean2D;

public class AxisIntervals2D extends IntervalsRange{

    Euclidean2D space;

    public AxisIntervals2D(Euclidean2D space){
        super(2);
        this.space = space;
    }

    public AxisIntervals2D(Euclidean2D space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(labelIntervals, rangeIntervalRatios);
        this.space = space;
    }

    public void updateLabelIntervals(){
        double xClipRange = space.xClipMax-space.xClipMin;
        updateLabelInterval(0, xClipRange);
        double yClipRange = space.yClipMax-space.yClipMin;
        updateLabelInterval(1, yClipRange);
    }
}