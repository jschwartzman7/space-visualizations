package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.Euclidean2D;

public class AxisIntervals2D extends IntervalsRange<Euclidean2D>{

    public AxisIntervals2D(Euclidean2D space){
        super(2);
        setSpace(space);
    }

    public AxisIntervals2D(Euclidean2D space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(labelIntervals, rangeIntervalRatios);
        setSpace(space);
    }

    public void updateLabelIntervals(){
        double xClipRange = space.xClipMax-space.xClipMin;
        updateLabelInterval(0, xClipRange);
        double yClipRange = space.yClipMax-space.yClipMin;
        updateLabelInterval(1, yClipRange);
    }
}