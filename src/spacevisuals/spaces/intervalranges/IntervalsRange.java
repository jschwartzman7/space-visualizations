package spacevisuals.spaces.intervalranges;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.SpaceUser;

public class IntervalsRange<T extends AbstractSpace> extends SpaceUser<T>{

    public static final double DEFAULT_LABEL_INTERVAL = 1;
    public static final double DEFAULT_RANGE_INTERVAL_MIN = 5;
    public static final double DEFAULT_RANGE_INTERVAL_MAX = 12;
    public double[] labelIntervals;
    public double[][] rangeIntervalRatios;


    public IntervalsRange(int numAxes){
        this.labelIntervals = new double[numAxes];
        this.rangeIntervalRatios = new double[numAxes][2];
        for(int i = 0; i < numAxes; i++){
            labelIntervals[i] = DEFAULT_LABEL_INTERVAL;
            rangeIntervalRatios[i][0] = DEFAULT_RANGE_INTERVAL_MIN;
            rangeIntervalRatios[i][1] = DEFAULT_RANGE_INTERVAL_MAX;
        }
    }
    public IntervalsRange(double[] labelIntervals, double[][] rangeIntervalRatios){
        this.labelIntervals = labelIntervals;
        this.rangeIntervalRatios = rangeIntervalRatios;
    }

    public void updateLabelInterval(int index, double range){
        double intervalRatio = range/labelIntervals[index];
        if(intervalRatio < rangeIntervalRatios[index][0]){
            this.labelIntervals[index] /= 2;
        }
        else if(intervalRatio > rangeIntervalRatios[index][1]){
            this.labelIntervals[index] *= 2;
        }
    }
    // override with ranges to pass to updateLabelInterval
    public void updateLabelIntervals(){};
}