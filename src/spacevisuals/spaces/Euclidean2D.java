package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.spaces.axesintervals.AxisIntervals2D;
import spacevisuals.spaces.spacemovers.SpaceMover2D;

public class Euclidean2D extends AbstractSpace{

    private final double X_LABEL_OFFSET = 0.02;
    private static Euclidean2D instance;

    private Euclidean2D(boolean viewSpaceInfo){
        super(viewSpaceInfo);
        this.dimensions = 2;
        initializeMover();
        initializeLabeler();
    }
    private Euclidean2D(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        super(defaultScale, moveSensitivity, viewSpaceInfo);
        this.dimensions = 2;
        initializeMover();
        initializeLabeler();
    }

    public static Euclidean2D Get(){
        if(instance == null){
            instance = new Euclidean2D(true);
        }
        return instance;
    }

    @Override
    public void initializeMover(){
        this.mover = new SpaceMover2D(this);
    }
    @Override
    public void initializeLabeler(){
        this.labeler = new AxisIntervals2D(2, DEFAULT_CLIP_SCALE, 3, 8);
    }
    @Override
    public void initializeColorScheme(){
        this.colorScheme = SpaceColorScheme.from("dark");
    }
    @Override
    public double[] toViewScreenPoint(double[] numericPoint){
        return new double[]{numericPoint[0], numericPoint[1]};
    }
    @Override
    public void updateView() {
        mover.updateView();
    }
    @Override
    public void updateLabelIntervals() {
        labeler.updateLabelIntervals();
    }
    @Override
    public void drawAxis(String label) {
        StdDraw.setPenRadius();
        double cord = 0;
        switch (label) {
            case "x":
                if(yClipMax < 0){
                    cord = yClipMax;
                }
                else if (yClipMin > 0){
                    cord = yClipMin;
                }
                StdDraw.setPenColor(colorScheme.xAxisColor);
                StdDraw.line(xClipMin, cord, xClipMax, cord);
                break;
            case "y":
                if(xClipMax < 0){
                    cord = xClipMax;
                }
                else if (xClipMin > 0){
                    cord = xClipMin;
                }
                StdDraw.setPenColor(colorScheme.yAxisColor);
                StdDraw.line(cord, yClipMin, cord, yClipMax);
                break;
            default:
                break;
        }
    }
    @Override
    public void drawLabels(){
        StdDraw.setPenColor(colorScheme.labelColor);
        StdDraw.setPenRadius(0.001);
        double numericMin = xClipMin;
        double numericMax = xClipMax;
        for(double numericX = numericMin-numericMin%labeler.getLabelIntervals()[0]; numericX <= numericMax; numericX += labeler.getLabelIntervals()[0]){
            if(Math.abs(numericX) < ZERO_TOLERANCE){continue;}
            double x = numericX;
            StdDraw.line(x, yClipMin, x, yClipMax);
            StdDraw.text(x, -(yClipMax-yClipMin)*X_LABEL_OFFSET, toLabel(numericX));
        }
        numericMin = yClipMin;
        numericMax = yClipMax;
        for(double numericY = numericMin-numericMin%labeler.getLabelIntervals()[1]; numericY <= numericMax; numericY += labeler.getLabelIntervals()[1]){
            if(Math.abs(numericY) < ZERO_TOLERANCE){continue;}
            double y = numericY;
            StdDraw.line(xClipMin, y, xClipMax, y);
            StdDraw.text(0, y, toLabel(numericY));
        }
    }
}

