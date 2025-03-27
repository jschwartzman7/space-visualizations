package spacevisuals.spaces.axesintervals;

import spacevisuals.SpaceUser;
import spacevisuals.helpers.IntervalsRange;
import spacevisuals.spaces.AbstractSpace;

public abstract class AxisIntervals<T extends AbstractSpace> extends SpaceUser<T>{

    public IntervalsRange labeler;

    public AxisIntervals(T space){
        super(space);
        this.labeler = new IntervalsRange(space.dimensions);
    }

    public AxisIntervals(T space, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        super(space);
        this.labeler = new IntervalsRange(space.dimensions, defaultInterval, rangeIntervalMin, rangeIntervalMax);
    }

    public abstract void updateLabelIntervals();
    
}
