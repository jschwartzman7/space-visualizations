package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.SpaceAnimation;
import spacevisuals.helpers.AxisLabeler;
/*
* Base class for Euclidean space to be rendered
* Extended by R2 and R3
*/
public abstract class AbstractSpace {
    protected final int CANVAS_WIDTH = 700;
    protected final int CANVAS_HEIGHT = 700;
    protected final double FLOAT_TOLERANCE = 0.000001;
    protected final double DEFAULT_CLIP_SCALE;
    protected final double MOVE_SENSITIVITY;
    protected final boolean VIEW_SPACE_INFO;
    public AxisLabeler labeler;
    public double xClipMin;
    public double xClipMax;
    public double yClipMin;
    public double yClipMax;
    public AbstractSpace(boolean viewSpaceInfo){
        this.DEFAULT_CLIP_SCALE = 3;
        this.MOVE_SENSITIVITY = 0.01;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        this.xClipMin = -DEFAULT_CLIP_SCALE;
        this.xClipMax = DEFAULT_CLIP_SCALE;
        this.yClipMin = -DEFAULT_CLIP_SCALE;
        this.yClipMax = DEFAULT_CLIP_SCALE;
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(-DEFAULT_CLIP_SCALE, DEFAULT_CLIP_SCALE);
        StdDraw.enableDoubleBuffering();
    }
    public AbstractSpace(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        this.DEFAULT_CLIP_SCALE = defaultScale;
        this.MOVE_SENSITIVITY = moveSensitivity;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        this.xClipMin = -defaultScale;
        this.xClipMax = defaultScale;
        this.yClipMin = -defaultScale;
        this.yClipMax = defaultScale;
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(-defaultScale, defaultScale);
        StdDraw.enableDoubleBuffering();
    }
    private double getXRange(){
        return xClipMax - xClipMin;
    }
    private double getYRange(){
        return yClipMax - yClipMin;
    }
    protected void translateXClipPos(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin += amount;
        this.xClipMax += amount;
    }
    protected void translateXClipNeg(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin -= amount;
        this.xClipMax -= amount;
    }
    protected void translateYClipPos(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin += amount;
        this.yClipMax += amount;
    }
    protected void translateYClipNeg(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin -= amount;
        this.yClipMax -= amount;
    }
    protected void zoomXClipOut(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin -= amount;
        this.xClipMax += amount;
    }
    protected void zoomXClipIn(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin += amount;
        this.xClipMax -= amount;
    }
    protected void zoomYClipOut(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin -= amount;
        this.yClipMax += amount;
    }
    protected void zoomYClipIn(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin += amount;
        this.yClipMax -= amount;
    }
    /*protected void adjustPrimaryDistortion(double amount){
        this.primaryDistortion += amount;
    }
    protected void adjustSecondaryDistortion(double amount){
        this.secondaryDistortion += amount;
    }*/
    /*protected void updateLabelIntervals(double primaryRange, double secondaryRange){
        double primaryIntervalRatio = primaryRange/primaryLabelInterval;
        if(primaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.primaryLabelInterval *= 2;
        }
        else if(primaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.primaryLabelInterval /= 2;
        }
        double secondaryIntervalRatio = secondaryRange/secondaryLabelInterval;
        if(secondaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            this.secondaryLabelInterval *= 2;
        }
        else if(secondaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            this.secondaryLabelInterval /= 2;
        }
    }*/
    protected void resetClipScale(){
        this.xClipMin = -DEFAULT_CLIP_SCALE;
        this.xClipMax = DEFAULT_CLIP_SCALE;
        this.yClipMin = -DEFAULT_CLIP_SCALE;
        this.yClipMax = DEFAULT_CLIP_SCALE;
    }
    public void setSpaceScale(){
        StdDraw.setXscale(xClipMin, xClipMax);
        StdDraw.setYscale(yClipMin, yClipMax);
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
        if(VIEW_SPACE_INFO){drawLabels();}
    };
    public abstract double[] toViewScreenPoint(double[] worldPoint);
    public abstract void updateView();
    public abstract void updateLabels();
    public abstract void drawAxes();
    public abstract void drawLabels();
}
