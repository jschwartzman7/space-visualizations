package spacevisuals.utils;

public class IntervalsRange{

    public static final double DEFAULT_INTERVAL = Constants.DEFAULT_LABEL_INTERVAL;
    public static final double DEFAULT_RANGE_INTERVAL_MIN = Constants.LABEL_INTERVAL_RANGE_MIN;
    public static final double DEFAULT_RANGE_INTERVAL_MAX = Constants.LABEL_INTERVAL_RANGE_MAX;
    public double[] labelIntervals;
    public double[][] rangeIntervalRatios;
    public int dimensions;

    public IntervalsRange(int dimensions){
        this.dimensions = dimensions;
        this.labelIntervals = new double[this.dimensions];
        this.rangeIntervalRatios = new double[this.dimensions][2];
        for(int i = 0; i < this.dimensions; i++){
            this.labelIntervals[i] = DEFAULT_INTERVAL;
            this.rangeIntervalRatios[i][0] = DEFAULT_RANGE_INTERVAL_MIN;
            this.rangeIntervalRatios[i][1] = DEFAULT_RANGE_INTERVAL_MAX;
        }
    }
    public IntervalsRange(int dimensions, double defaultInterval, double rangeIntervalMin, double rangeIntervalMax){
        this.labelIntervals = new double[dimensions];
        this.rangeIntervalRatios = new double[dimensions][2];
        for(int i = 0; i < dimensions; i++){
            this.labelIntervals[i] = defaultInterval;
            this.rangeIntervalRatios[i][0] = rangeIntervalMin;
            this.rangeIntervalRatios[i][1] = rangeIntervalMax;
        }
    }
    
    public void updateLabelInterval(double range, int index){
        double intervalRatio = range/labelIntervals[index];
        if(intervalRatio < rangeIntervalRatios[index][0]){
            labelIntervals[index] /= Constants.LABEL_INTERVAL_SCALE_FACTOR;
        }
        else if(intervalRatio > rangeIntervalRatios[index][1]){
            labelIntervals[index] *= Constants.LABEL_INTERVAL_SCALE_FACTOR;
        }
    }
    
}