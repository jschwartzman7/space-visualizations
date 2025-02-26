package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.spaces.intervalranges.IntervalsRange;
import spacevisuals.spaces.spacemovers.SpaceMover;;
/*
* Base class for Euclidean space to be rendered
* Extended by Euclidean2D, Euclidean3D, Euclidean4D
*/
public abstract class AbstractSpace {
    static final boolean DEFAULT_VIEW_SPACE_INFO = true;
    public final double ZERO_TOLERANCE = 0.000001;
    public final double DEFAULT_CLIP_SCALE;
    public final double MOVE_SENSITIVITY;
    public final boolean VIEW_SPACE_INFO;
    public double xClipMin;
    public double xClipMax;
    public double yClipMin;
    public double yClipMax;
    public SpaceMover mover;
    public IntervalsRange labeler;
    public SpaceColorScheme colorScheme;
    public AbstractSpace(){
        this.DEFAULT_CLIP_SCALE = 3;
        this.MOVE_SENSITIVITY = 0.025;
        this.VIEW_SPACE_INFO = DEFAULT_VIEW_SPACE_INFO;
        initializeSpaceVariables();
    }
    public AbstractSpace(boolean viewSpaceInfo){
        this.DEFAULT_CLIP_SCALE = 3;
        this.MOVE_SENSITIVITY = 0.025;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        initializeSpaceVariables();
    }
    public AbstractSpace(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        this.DEFAULT_CLIP_SCALE = defaultScale;
        this.MOVE_SENSITIVITY = moveSensitivity;
        this.VIEW_SPACE_INFO = viewSpaceInfo;
        initializeSpaceVariables();
    }
    private void initializeSpaceVariables(){
        this.xClipMin = -DEFAULT_CLIP_SCALE;
        this.xClipMax = DEFAULT_CLIP_SCALE;
        this.yClipMin = -DEFAULT_CLIP_SCALE;
        this.yClipMax = DEFAULT_CLIP_SCALE;
        initializeMover();
        initializeLabeler();
        initializeColorScheme();
    }
    public double getXRange(){
        return xClipMax - xClipMin;
    }
    public double getYRange(){
        return yClipMax - yClipMin;
    }
    public void translateXClipPos(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin += amount;
        this.xClipMax += amount;
    }
    public void translateXClipNeg(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin -= amount;
        this.xClipMax -= amount;
    }
    public void translateYClipPos(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin += amount;
        this.yClipMax += amount;
    }
    public void translateYClipNeg(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin -= amount;
        this.yClipMax -= amount;
    }
    public void zoomXClipOut(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin -= amount;
        this.xClipMax += amount;
    }
    public void zoomXClipIn(){
        double amount = MOVE_SENSITIVITY*getXRange();
        this.xClipMin += amount;
        this.xClipMax -= amount;
    }
    public void zoomYClipOut(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin -= amount;
        this.yClipMax += amount;
    }
    public void zoomYClipIn(){
        double amount = MOVE_SENSITIVITY*getYRange();
        this.yClipMin += amount;
        this.yClipMax -= amount;
    }
    public void resetClipScale(){
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
        mover.updateView();
        setSpaceScale();
        if(VIEW_SPACE_INFO){labeler.updateLabelIntervals();}
    };
    public void drawSpace(){
        drawAxes();
        if(VIEW_SPACE_INFO){drawLabels();}
    };
    public abstract void initializeMover();
    public abstract void initializeLabeler();
    public abstract void initializeColorScheme();
    public abstract double[] toViewScreenPoint(double[] worldPoint);
    public abstract void drawAxes();
    public abstract void drawLabels();
}
