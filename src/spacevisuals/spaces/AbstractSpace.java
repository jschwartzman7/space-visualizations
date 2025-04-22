package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.enums.VariableEnum;
import spacevisuals.spaces.axesintervals.AxisIntervals;
import spacevisuals.spaces.spacemovers.SpaceMover;
import spacevisuals.utils.Constants;
/*
* Base class for Euclidean space to be rendered
* Extended by Euclidean2D, Euclidean3D, Euclidean4D
*/
public abstract class AbstractSpace {
    private static final double DEFAULT_DEFAULT_CLIP_SCALE = Constants.DEFAULT_CLIP_RADIUS;
    private static final double DEFAULT_MOVE_SENSITIVITY = Constants.MOVE_SENSITIVITY;
    public final double DEFAULT_CLIP_SCALE;
    public final double MOVE_SENSITIVITY;
    public final boolean VIEW_SPACE_INFO;
    public double xClipMin;
    public double xClipMax;
    public double yClipMin;
    public double yClipMax;
    public SpaceColorScheme colorScheme;
    public SpaceMover mover;
    public AxisIntervals labeler;
    public int dimensions;

    public AbstractSpace(boolean viewSpaceInfo){
        this.DEFAULT_CLIP_SCALE = DEFAULT_DEFAULT_CLIP_SCALE;
        this.MOVE_SENSITIVITY = DEFAULT_MOVE_SENSITIVITY;
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
        initializeColorScheme();
        initializeMover();
        initializeLabeler();
    }
    public double getXRange(){
        return this.xClipMax - this.xClipMin;
    }
    public double getYRange(){
        return this.yClipMax - this.yClipMin;
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
    protected double[][][] partitionAxis(double[][] axis, int axisIndex){
        int numPartitions = Constants.AXIS_PARTITIONS_HIGH;
        double[][][] partitionedAxis = new double[numPartitions][2][axis[0].length];
        double partitionLength = (axis[1][axisIndex]-axis[0][axisIndex])/numPartitions;
        for(int i = 0; i < numPartitions; i++){
            partitionedAxis[i][0][axisIndex] = axis[0][axisIndex]+i*partitionLength;
            partitionedAxis[i][1][axisIndex] = axis[0][axisIndex]+(i+1)*partitionLength;
        }
        return partitionedAxis;
    }
    public void drawAxes(){
        for(int i = 0 ; i < dimensions; i++){
            drawAxis(VariableEnum.fromPrecedence(i).toString());
        }
    };
    public void updateSpace(){
        updateView();
        setSpaceScale();
        if(VIEW_SPACE_INFO){updateLabelIntervals();}
    };
    public void drawSpace(){
        drawAxes();
        if(VIEW_SPACE_INFO){drawLabels();}
    };
    public abstract void initializeColorScheme();
    public abstract void initializeMover();
    public abstract void initializeLabeler();
    public abstract double[] toViewScreenPoint(double[] worldPoint);
    public abstract void updateView();
    public abstract void updateLabelIntervals();
    public abstract void drawAxis(String label);
    public abstract void drawLabels();
}
