package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
/*
* Base class for Euclidean space to be rendered
* Extended by R2 and R3
*/
public abstract class AbstractSpace {
    protected final int CANVAS_WIDTH = 700;
    protected final int CANVAS_HEIGHT = 700;
    protected final double FLOAT_TOLERANCE = 0.000001;
    protected final int DEFAULT_CLIP_SCALE;
    protected final boolean VIEW_SPACE_INFO;
    protected final double DEFAULT_LABEL_INTERVAL;
    protected final double RANGE_INTERVAL_RATIO_MIN;
    protected final double RANGE_INTERVAL_RATIO_MAX;
    protected final double MOVE_SENSITIVITY;
    public double primaryLabelInterval;
    public double secondaryLabelInterval;
    //public double primaryDistortion;
    //public double secondaryDistortion;
    public double xMinClip;
    public double xMaxClip;
    public double yMinClip;
    public double yMaxClip;
    public AbstractSpace(int defaultScale, double defaultLabelInterval, boolean viewSpaceInfo, int rangeIntervalRatioMin, int rangeIntervalRatioMax, double moveSensitivity){
        this.DEFAULT_CLIP_SCALE = defaultScale;
        this.DEFAULT_LABEL_INTERVAL = defaultLabelInterval;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        this.RANGE_INTERVAL_RATIO_MIN = rangeIntervalRatioMin;
        this.RANGE_INTERVAL_RATIO_MAX = rangeIntervalRatioMax;
        this.MOVE_SENSITIVITY = moveSensitivity;
        this.primaryLabelInterval = defaultLabelInterval;
        this.secondaryLabelInterval = defaultLabelInterval;
        //this.primaryDistortion = 1;
        //this.secondaryDistortion = 1;
        this.xMinClip = -defaultScale;
        this.xMaxClip = defaultScale;
        this.yMinClip = -defaultScale;
        this.yMaxClip = defaultScale;
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(-defaultScale, defaultScale);
        StdDraw.enableDoubleBuffering();
    }
    protected void translateXClip(double amount){
        this.xMinClip += amount;
        this.xMaxClip += amount;
    }
    protected void translateYClip(double amount){
        this.yMinClip += amount;
        this.yMaxClip += amount;
    }
    protected void zoomXClip(double amount){
        this.xMinClip -= amount;
        this.xMaxClip += amount;
    }
    protected void zoomYClip(double amount){
        this.yMinClip -= amount;
        this.yMaxClip += amount;
    }
    /*protected void adjustPrimaryDistortion(double amount){
        this.primaryDistortion += amount;
    }
    protected void adjustSecondaryDistortion(double amount){
        this.secondaryDistortion += amount;
    }*/
    protected void updateLabelIntervals(double primaryRange, double secondaryRange){
        double distortedPrimaryIntervalRatio = primaryRange/primaryLabelInterval;
        if(distortedPrimaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.primaryLabelInterval *= 2;
        }
        else if(distortedPrimaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.primaryLabelInterval /= 2;
        }
        double distortedSecondaryIntervalRatio = secondaryRange/secondaryLabelInterval;
        if(distortedSecondaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.secondaryLabelInterval *= 2;
        }
        else if(distortedSecondaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.secondaryLabelInterval /= 2;
        }
    }
    protected void resetView(){
        this.xMinClip = -DEFAULT_CLIP_SCALE;
        this.xMaxClip = DEFAULT_CLIP_SCALE;
        this.yMinClip = -DEFAULT_CLIP_SCALE;
        this.yMaxClip = DEFAULT_CLIP_SCALE;
        this.primaryLabelInterval = DEFAULT_LABEL_INTERVAL;
        this.secondaryLabelInterval = DEFAULT_LABEL_INTERVAL;
        //this.primaryDistortion = 1;
        //this.secondaryDistortion = 1;
    }
    public void setSpaceScale(){
        StdDraw.setXscale(xMinClip, xMaxClip);
        StdDraw.setYscale(yMinClip, yMaxClip);
    }
    public String toLabel(double number){
        return number == (int)number ? (int)number+"" : Math.round((number*100))/100.0+"";
    }
    public void updateSpace(){
        updateView();
        if(VIEW_SPACE_INFO){updateLabels();}
    };

    public void drawSpace(){
        drawAxes();
        if(VIEW_SPACE_INFO){drawSpaceInfo();}
    };
    public abstract double[] toDrawablePoint(double[] worldPoint);
    public abstract void updateView();
    public abstract void updateLabels();
    public abstract void drawAxes();
    public abstract void drawSpaceInfo();
    
}
