package spacevisuals.spaces.axesintervals;

import spacevisuals.spaces.SpaceUser3D;
import spacevisuals.utils.IntervalsRange;

public class AxisIntervals3D implements SpaceUser3D, AxisIntervals{

    public IntervalsRange labeler;

    public AxisIntervals3D(int dimensions, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        this.labeler = new IntervalsRange(dimensions, defaultInterval, rangeIntervalMin, rangeIntervalMax);
    }
    public void updateLabelIntervals(){
        //double focalLengthDistortion = getSpace().camera.DEFAULT_FOCAL_LENGTH/getSpace().camera.focalLength;
        double primaryAxesRange = Math.min(space().xClipMax-space().xClipMin, space().yClipMax-space().yClipMin);
        labeler.updateLabelInterval(primaryAxesRange, 0);
        labeler.updateLabelInterval(primaryAxesRange, 1);
        labeler.updateLabelInterval(primaryAxesRange, 2);
    }
    @Override
    public double[] getLabelIntervals() {
        return labeler.labelIntervals;
    }
}