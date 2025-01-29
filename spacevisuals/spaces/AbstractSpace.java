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
    protected final int DEFAULT_SCALE;
    protected final double DEFAULT_LABEL_INTERVAL;
    protected final boolean VIEW_SPACE_INFO;
    protected final double RANGE_INTERVAL_RATIO_MIN;
    protected final double RANGE_INTERVAL_RATIO_MAX;
    protected final double TRANSLATION_SENSITIVITY;
    protected final double ZOOM_SENSITIVITY;
    public double primaryLabelInterval;
    public double secondaryLabelInterval;
    public double primaryDistortion;
    public double secondaryDistortion;
    public double X_MIN;
    public double X_MAX;
    public double Y_MIN;
    public double Y_MAX;
    public AbstractSpace(int defaultScale, double defaultLabelInterval, boolean viewSpaceInfo, int rangeIntervalRatioMin, int rangeIntervalRatioMax, double translationSensitivity, double zoomSensitivity){
        this.DEFAULT_SCALE = defaultScale;
        this.DEFAULT_LABEL_INTERVAL = defaultLabelInterval;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        this.RANGE_INTERVAL_RATIO_MIN = rangeIntervalRatioMin;
        this.RANGE_INTERVAL_RATIO_MAX = rangeIntervalRatioMax;
        this.TRANSLATION_SENSITIVITY = translationSensitivity;
        this.ZOOM_SENSITIVITY = zoomSensitivity;
        this.primaryLabelInterval = defaultLabelInterval;
        this.secondaryLabelInterval = defaultLabelInterval;
        this.primaryDistortion = 1;
        this.secondaryDistortion = 1;
        this.X_MIN = -defaultScale;
        this.X_MAX = defaultScale;
        this.Y_MIN = -defaultScale;
        this.Y_MAX = defaultScale;
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(-defaultScale, defaultScale);
        StdDraw.enableDoubleBuffering();
    }
    protected void translateX(double amount){
        this.X_MIN += amount;
        this.X_MAX += amount;
    }
    protected void translateY(double amount){
        this.Y_MIN += amount;
        this.Y_MAX += amount;
    }
    protected void zoomX(double amount){
        this.X_MIN -= amount;
        this.X_MAX += amount;
    }
    protected void zoomY(double amount){
        this.Y_MIN -= amount;
        this.Y_MAX += amount;
    }
    protected void adjustPrimaryDistortion(double amount){
        this.primaryDistortion += amount;
    }
    protected void adjustSecondaryDistortion(double amount){
        this.secondaryDistortion += amount;
    }
    protected void updateLabelIntervals(double primaryRange, double secondaryRange){
        double distortedPrimaryIntervalRatio = primaryRange*primaryDistortion/primaryLabelInterval;
        if(distortedPrimaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.primaryLabelInterval *= 2;
        }
        else if(distortedPrimaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.primaryLabelInterval /= 2;
        }
        double distortedSecondaryIntervalRatio = secondaryRange*secondaryDistortion/secondaryLabelInterval;
        if(distortedSecondaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.secondaryLabelInterval *= 2;
        }
        else if(distortedSecondaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.secondaryLabelInterval /= 2;
        }
    }
    protected void resetView(){
        this.X_MIN = -DEFAULT_SCALE;
        this.X_MAX = DEFAULT_SCALE;
        this.Y_MIN = -DEFAULT_SCALE;
        this.Y_MAX = DEFAULT_SCALE;
        this.primaryLabelInterval = DEFAULT_LABEL_INTERVAL;
        this.secondaryLabelInterval = DEFAULT_LABEL_INTERVAL;
        this.primaryDistortion = 1;
        this.secondaryDistortion = 1;
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
    public abstract double[] toDrawablePoint(double[] numericPoint);
    public abstract void updateView();
    public abstract void updateLabels();
    public abstract void drawAxes();
    public abstract void drawSpaceInfo();
    
}
