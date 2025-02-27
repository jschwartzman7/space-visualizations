package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;

public class IntervalsRange<T extends AbstractSpace> extends SpaceUser<T>{

    public static final double DEFAULT_INTERVAL = 1;
    public static final double DEFAULT_RANGE_INTERVAL_MIN = 5;
    public static final double DEFAULT_RANGE_INTERVAL_MAX = 12;
    public double[] labelIntervals;
    public double[][] rangeIntervalRatios;

    public IntervalsRange(T space){
        super(space);
    }
    public IntervalsRange(T space, double[] labelIntervals, double[][] rangeIntervalRatios){
        super(space);
        this.labelIntervals = labelIntervals;
        this.rangeIntervalRatios = rangeIntervalRatios;
    }

    public double updateLabelInterval(double range, double interval){
        double intervalRatio = range/interval;
        if(intervalRatio < DEFAULT_RANGE_INTERVAL_MIN){
            interval /= 2;
        }
        else if(intervalRatio > DEFAULT_RANGE_INTERVAL_MAX){
            interval *= 2;
        }
        return interval;
    }
    public double updateLabelInterval(double range, double interval, double intervalMin, double intervalMax){
        double intervalRatio = range/interval;
        if(intervalRatio < intervalMin){
            interval /= 2;
        }
        else if(intervalRatio > intervalMax){
            interval *= 2;
        }
        return interval;
    }

    public void updateLabelIntervals(){};
}