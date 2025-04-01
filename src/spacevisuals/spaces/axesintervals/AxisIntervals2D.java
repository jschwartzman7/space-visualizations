package spacevisuals.spaces.axesintervals;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.utils.IntervalsRange;

public class AxisIntervals2D implements SpaceUser2D, AxisIntervals{

    
    public IntervalsRange labeler;

    public AxisIntervals2D(int dimensions, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        this.labeler = new IntervalsRange(dimensions, defaultInterval, rangeIntervalMin, rangeIntervalMax);
    }
    public void updateLabelIntervals(){
        double xClipRange = space().xClipMax-space().xClipMin;
        labeler.updateLabelInterval(xClipRange, 0);
        double yClipRange = space().yClipMax-space().yClipMin;
        labeler.updateLabelInterval(yClipRange, 1);
    }
    @Override
    public double[] getLabelIntervals() {
        return labeler.labelIntervals;
    }
}