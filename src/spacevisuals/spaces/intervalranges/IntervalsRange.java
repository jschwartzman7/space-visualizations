package spacevisuals.spaces.intervalranges;

public class IntervalsRange{

    public static final double DEFAULT_INTERVAL = 1;
    public static final double DEFAULT_RANGE_INTERVAL_MIN = 8;
    public static final double DEFAULT_RANGE_INTERVAL_MAX = 20;
    public double[] labelIntervals;
    public double[][] rangeIntervalRatios;
    public int dimensions;

    public IntervalsRange(){
        this.dimensions = 1;
        this.labelIntervals = new double[dimensions];
        this.rangeIntervalRatios = new double[dimensions][2];
        for(int i = 0; i < dimensions; i++){
            this.labelIntervals[i] = DEFAULT_INTERVAL;
            this.rangeIntervalRatios[i][0] = DEFAULT_RANGE_INTERVAL_MIN;
            this.rangeIntervalRatios[i][1] = DEFAULT_RANGE_INTERVAL_MAX;
        }
    }
    public IntervalsRange(int dimensions){
        this.labelIntervals = new double[dimensions];
        this.rangeIntervalRatios = new double[dimensions][2];
        for(int i = 0; i < dimensions; i++){
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
    public void updateLabelInterval(double range){
        double intervalRatio = range/labelIntervals[0];
        if(intervalRatio < DEFAULT_RANGE_INTERVAL_MIN){
            labelIntervals[0] /= 2;
        }
        else if(intervalRatio > DEFAULT_RANGE_INTERVAL_MAX){
            labelIntervals[0] *= 2;
        }
    }
    public void updateLabelInterval(double range, int index){
        double intervalRatio = range/labelIntervals[index];
        if(intervalRatio < DEFAULT_RANGE_INTERVAL_MIN){
            labelIntervals[index] /= 2;
        }
        else if(intervalRatio > DEFAULT_RANGE_INTERVAL_MAX){
            labelIntervals[index] *= 2;
        }
    }
    
}