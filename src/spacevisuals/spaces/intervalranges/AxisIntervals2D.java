package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.SpaceUser;

public class AxisIntervals2D extends SpaceUser<Euclidean2D>{

    public IntervalsRange labeler;

    public AxisIntervals2D(Euclidean2D space){
        super(space);
        this.labeler = new IntervalsRange(2);
    }

    public AxisIntervals2D(Euclidean2D space, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        super(space);
        this.labeler = new IntervalsRange(2, defaultInterval, rangeIntervalMin, rangeIntervalMax);
    }

    public void updateLabelIntervals(){
        double xClipRange = space.xClipMax-space.xClipMin;
        labeler.updateLabelInterval(xClipRange, 0);
        double yClipRange = space.yClipMax-space.yClipMin;
        labeler.updateLabelInterval(yClipRange, 1);
    }
}