package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class AxisIntervals3D extends SpaceUser<Euclidean3D>{

    public IntervalsRange labeler;

    public AxisIntervals3D(Euclidean3D space){
        super(space);
        this.labeler = new IntervalsRange(3);
    }

    public AxisIntervals3D(Euclidean3D space, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        super(space);
        this.labeler = new IntervalsRange(3, defaultInterval, rangeIntervalMin, rangeIntervalMax);
    }

    public void updateLabelIntervals(){
        double clipRange = Math.min(space.xClipMax-space.xClipMin, space.yClipMax-space.yClipMin);
        labeler.updateLabelInterval(clipRange, 0);
        labeler.updateLabelInterval(clipRange, 1);
        labeler.updateLabelInterval(clipRange, 2);
    }
}