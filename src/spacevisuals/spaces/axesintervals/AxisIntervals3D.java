package spacevisuals.spaces.axesintervals;

import spacevisuals.spaces.Euclidean3D;

public class AxisIntervals3D extends AxisIntervals<Euclidean3D>{

    public AxisIntervals3D(Euclidean3D space){
        super(space);
    }

    public AxisIntervals3D(Euclidean3D space, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        super(space);
    }

    public void updateLabelIntervals(){
        //double focalLengthDistortion = getSpace().camera.DEFAULT_FOCAL_LENGTH/getSpace().camera.focalLength;
        double primaryAxesRange = Math.min(getSpace().xClipMax-getSpace().xClipMin, getSpace().yClipMax-getSpace().yClipMin);
        labeler.updateLabelInterval(primaryAxesRange, 0);
        labeler.updateLabelInterval(primaryAxesRange, 1);
        labeler.updateLabelInterval(primaryAxesRange, 2);
    }
}