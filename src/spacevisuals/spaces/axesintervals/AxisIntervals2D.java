package spacevisuals.spaces.axesintervals;

import spacevisuals.spaces.Euclidean2D;

public class AxisIntervals2D extends AxisIntervals<Euclidean2D>{

    public AxisIntervals2D(Euclidean2D space){
        super(space);
    }

    public AxisIntervals2D(Euclidean2D space, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        super(space);
    }

    public void updateLabelIntervals(){
        double xClipRange = getSpace().xClipMax-getSpace().xClipMin;
        labeler.updateLabelInterval(xClipRange, 0);
        double yClipRange = getSpace().yClipMax-getSpace().yClipMin;
        labeler.updateLabelInterval(yClipRange, 1);
    }
}