import edu.princeton.cs.introcs.StdDraw;
/*
* Base class for Euclidean space to be rendered
* Extended by R2 and R3
*/
public abstract class abstractSpaceVisual {
    final int CANVAS_WIDTH = 700;
    final int CANVAS_HEIGHT = 700;
    final double FLOAT_TOLERANCE = 0.000001;
    final int DEFAULT_SCALE;
    final double DEFAULT_LABEL_INTERVAL;
    final boolean VIEW_SPACE_INFO;
    final double RANGE_INTERVAL_RATIO_MIN;
    final double RANGE_INTERVAL_RATIO_MAX;
    final double TRANSLATION_SENSITIVITY;
    final double ZOOM_SENSITIVITY;
    double X_MIN;
    double X_MAX;
    double Y_MIN;
    double Y_MAX;
    double primaryLabelInterval;
    double secondaryLabelInterval;
    public abstractSpaceVisual(int defaultScale, double defaultLabelInterval, boolean viewSpaceInfo, int rangeIntervalRatioMin, int rangeIntervalRatioMax, double translationSensitivity, double zoomSensitivity){
        this.DEFAULT_SCALE = defaultScale;
        this.DEFAULT_LABEL_INTERVAL = defaultLabelInterval;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        this.RANGE_INTERVAL_RATIO_MIN = rangeIntervalRatioMin;
        this.RANGE_INTERVAL_RATIO_MAX = rangeIntervalRatioMax;
        this.TRANSLATION_SENSITIVITY = translationSensitivity;
        this.ZOOM_SENSITIVITY = zoomSensitivity;
        this.primaryLabelInterval = defaultLabelInterval;
        this.secondaryLabelInterval = defaultLabelInterval;
        this.X_MIN = -defaultScale;
        this.X_MAX = defaultScale;
        this.Y_MIN = -defaultScale;
        this.Y_MAX = defaultScale;
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(-defaultScale, defaultScale);
        StdDraw.enableDoubleBuffering();
    }
    public void translateX(double amount){
        this.X_MIN += amount;
        this.X_MAX += amount;
    }
    public void translateY(double amount){
        this.Y_MIN += amount;
        this.Y_MAX += amount;
    }
    public void zoomX(double amount){
        this.X_MIN -= amount;
        this.X_MAX += amount;
    }
    public void zoomY(double amount){
        this.Y_MIN -= amount;
        this.Y_MAX += amount;
    }
    public void updateLabelIntervals(double primaryIntervalRatio, double secondaryIntervalRatio){
        if(primaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.primaryLabelInterval *= 2;
        }
        else if(primaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.primaryLabelInterval /= 2;
        }
        if(secondaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.secondaryLabelInterval *= 2;
        }
        else if(secondaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.secondaryLabelInterval /= 2;
        }
    }
    public void resetView(){
        this.X_MIN = -DEFAULT_SCALE;
        this.X_MAX = DEFAULT_SCALE;
        this.Y_MIN = -DEFAULT_SCALE;
        this.Y_MAX = DEFAULT_SCALE;
        this.primaryLabelInterval = DEFAULT_LABEL_INTERVAL;
        this.secondaryLabelInterval = DEFAULT_LABEL_INTERVAL;
    }
    public String toLabel(double number){
        return number == (int)number ? (int)number+"" : Math.round((number*100))/100.0+"";
    }
    // Checks for user key input and updates the view accordingly
    abstract void updateView();
    // Calls StdDraw methods to draw the current space
    abstract void drawSpace();
}
