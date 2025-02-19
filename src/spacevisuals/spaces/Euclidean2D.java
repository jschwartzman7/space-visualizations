package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.colors.ColorScheme;
import spacevisuals.spaces.axisintervals.*;
import spacevisuals.spaces.spacemovers.DefaultSpaceMover2D;

public class Euclidean2D extends AbstractSpace{

    private final double X_LABEL_OFFSET = 0.02;
    private static final boolean DEFAULT_VIEW_SPACE_INFO = true;
    private static Euclidean2D instance;

    private Euclidean2D(boolean viewSpaceInfo){
        super(viewSpaceInfo);
    }
    private Euclidean2D(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        super(defaultScale, moveSensitivity, viewSpaceInfo);
    }
    public static Euclidean2D Euclidean2DGet(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        instance = new Euclidean2D(defaultScale, moveSensitivity, viewSpaceInfo);
        return instance;
    }
    public static Euclidean2D Euclidean2DGet(){
        if(instance == null){
            instance = new Euclidean2D(DEFAULT_VIEW_SPACE_INFO);
        }
        return instance;
    }

    public void initializeMover(){
        this.mover = new DefaultSpaceMover2D(this);
    }
    public void initializeLabeler(){
        this.labeler = new AxisIntervals2D(this, new double[]{DEFAULT_CLIP_SCALE, DEFAULT_CLIP_SCALE}, new double[][]{{3, 8}, {3, 8}});
    }
    public void initializeColorScheme(){
        this.colorScheme = new ColorScheme("");
    }
    public double[] toViewScreenPoint(double[] numericPoint){
        return new double[]{numericPoint[0], numericPoint[1]};
    }

    public void drawAxes(){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(colorScheme.xAxisColor);
        double yCord;
        if(yClipMax < 0){
            yCord = yClipMax;
        }
        else if (yClipMin > 0){
            yCord = yClipMin;
        }
        else{
            yCord = 0;
        }
        StdDraw.line(xClipMin, yCord, xClipMax, yCord);
        StdDraw.setPenColor(colorScheme.yAxisColor);
        double xCord;
        if(xClipMax < 0){
            xCord = xClipMax;
        }
        else if (xClipMin > 0){
            xCord = xClipMin;
        }
        else{
            xCord = 0;
        }
        StdDraw.line(xCord, yClipMin, xCord, yClipMax);
    }

    public void drawLabels(){
        StdDraw.setPenColor(colorScheme.labelColor);
        StdDraw.setPenRadius(0.001);
        double numericMin = xClipMin;
        double numericMax = xClipMax;
        for(double numericX = numericMin-numericMin%labeler.labelIntervals[0]; numericX <= numericMax; numericX += labeler.labelIntervals[0]){
            if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
            double x = numericX;
            StdDraw.line(x, yClipMin, x, yClipMax);
            StdDraw.text(x, -(yClipMax-yClipMin)*X_LABEL_OFFSET, toLabel(numericX));
        }
        numericMin = yClipMin;
        numericMax = yClipMax;
        for(double numericY = numericMin-numericMin%labeler.labelIntervals[1]; numericY <= numericMax; numericY += labeler.labelIntervals[1]){
            if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
            double y = numericY;
            StdDraw.line(xClipMin, y, xClipMax, y);
            StdDraw.text(0, y, toLabel(numericY));
        }
    }
}

