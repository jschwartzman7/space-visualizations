package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.spaces.intervalranges.AxisIntervals2D;
import spacevisuals.spaces.spacemovers.SpaceMover;
import spacevisuals.spaces.spacemovers.SpaceMover2D;

public class Euclidean2D extends AbstractSpace{

    private final double X_LABEL_OFFSET = 0.02;
    public SpaceMover mover;
    public AxisIntervals2D labeler;
    private static SingletonSpace<Euclidean2D> spaceSingleton = new SingletonSpace<Euclidean2D>();
    private Euclidean2D(){
        super();
        this.dimensions = 2;
        initializeMover();
        initializeLabeler();
    }
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
    public static Euclidean2D Get(boolean viewSpaceInfo){
        return spaceSingleton.getOrCreateSpace(() -> new Euclidean2D(viewSpaceInfo));
    }
    public static Euclidean2D Get(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        return spaceSingleton.getOrCreateSpace(() -> new Euclidean2D(defaultScale, moveSensitivity, viewSpaceInfo));
    }
    public static Euclidean2D Get(){
        return spaceSingleton.getOrCreateSpace(Euclidean2D::new);
    }

    public void initializeMover(){
        this.mover = new SpaceMover2D(this);
    }
    public void initializeLabeler(){
        this.labeler = new AxisIntervals2D(this, DEFAULT_CLIP_SCALE, 3, 8);
    }
    public void initializeColorScheme(){
        this.colorScheme = SpaceColorScheme.from("default");
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
        for(double numericX = numericMin-numericMin%labeler.labeler.labelIntervals[0]; numericX <= numericMax; numericX += labeler.labeler.labelIntervals[0]){
            if(Math.abs(numericX) < ZERO_TOLERANCE){continue;}
            double x = numericX;
            StdDraw.line(x, yClipMin, x, yClipMax);
            StdDraw.text(x, -(yClipMax-yClipMin)*X_LABEL_OFFSET, toLabel(numericX));
        }
        numericMin = yClipMin;
        numericMax = yClipMax;
        for(double numericY = numericMin-numericMin%labeler.labeler.labelIntervals[1]; numericY <= numericMax; numericY += labeler.labeler.labelIntervals[1]){
            if(Math.abs(numericY) < ZERO_TOLERANCE){continue;}
            double y = numericY;
            StdDraw.line(xClipMin, y, xClipMax, y);
            StdDraw.text(0, y, toLabel(numericY));
        }
    }
    @Override
    public void updateView() {
        mover.updateView();
    }
    @Override
    public void updateLabelIntervals() {
        labeler.updateLabelIntervals();
    }
}

