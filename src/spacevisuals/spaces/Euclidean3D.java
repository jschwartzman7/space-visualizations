package spacevisuals.spaces;

import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.enums.VariableEnum;
import spacevisuals.spaces.axesintervals.AxisIntervals3D;
import spacevisuals.spaces.spacemovers.SpaceMover3D;
import spacevisuals.utils.Camera3D;
import spacevisuals.utils.Constants;
import edu.princeton.cs.introcs.StdDraw;
/*
 * adjust label interval as distortion changes, not just displayed value
 */
public class Euclidean3D extends AbstractSpace{


    private static final boolean DEFAULT_VIEW_SPACE_INFO = true;
    public static final double DEFAULT_AXES_SCALE = Constants.DEFAULT_AXIS_RADIUS;
    public double xAxisMin;
    public double xAxisMax;
    public double yAxisMin;
    public double yAxisMax;
    public double zAxisMin;
    public double zAxisMax;
    public Camera3D camera;
    private static Euclidean3D instance;
    
    private Euclidean3D(boolean viewSpaceInfo){
        super(viewSpaceInfo);
        this.dimensions = 3;
        initializeVariables(DEFAULT_AXES_SCALE);
    }
    private Euclidean3D(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        super(defaultScale, moveSensitivity, viewSpaceInfo);
        this.dimensions = 3;
        initializeVariables(DEFAULT_AXES_SCALE);
    }
    public static Euclidean3D Get(){
        if(instance == null){
            instance = new Euclidean3D(DEFAULT_VIEW_SPACE_INFO);
        }
        return instance;
    }

    public void initializeVariables(double scale){
        this.xAxisMin = -scale;
        this.xAxisMax = scale;
        this.yAxisMin = -scale;
        this.yAxisMax = scale;
        this.zAxisMin = -scale;
        this.zAxisMax = scale;
        this.camera = new Camera3D();
        initializeColorScheme();
        initializeMover();
        initializeLabeler();
        this.dimensions = 3;
    }

    public void initializeMover(){
        this.mover = new SpaceMover3D(this);
    }
    public void initializeLabeler(){
        this.labeler = new AxisIntervals3D(3, DEFAULT_CLIP_SCALE, 3, 8);
    }

    @Override
    public void initializeColorScheme(){
        this.colorScheme = SpaceColorScheme.from("dark");
    }
    @Override
    public double[] toViewScreenPoint(double[] numericPoint){
        return camera.toDrawablePoint(numericPoint);
        /*double[] rotatedPoint = matrixUtils.matrixVectorRmxnRn_Rm(c, new double[]{numericPoint[0], numericPoint[1], numericPoint[2]});
        return new double[] {Math.sqrt(3)/2*(rotatedPoint[1]-rotatedPoint[0]), rotatedPoint[2] - .5*(rotatedPoint[1]+rotatedPoint[0])};*/
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
    public void drawAxis(String label){
        double axis [][] = new double[2][3];
        switch(label){
            case "x":
                axis = new double[][]{{xAxisMin, 0, 0}, {xAxisMax, 0, 0}};
                StdDraw.setPenColor(colorScheme.xAxisColor);
                break;
            case "y":
                axis = new double[][]{{0, yAxisMin, 0}, {0, yAxisMax, 0}};
                StdDraw.setPenColor(colorScheme.yAxisColor);
                break;
            case "z":
                axis = new double[][]{{0, 0, zAxisMin}, {0, 0, zAxisMax}};
                StdDraw.setPenColor(colorScheme.zAxisColor);
                break;
        }
        double[][][] partitionedAxis = partitionAxis(axis, VariableEnum.valueOf(label).precedence);
        StdDraw.setPenRadius();
        for(double[][] partition : partitionedAxis){
            double[] axisP12D = toViewScreenPoint(new double[]{partition[0][0], partition[0][1], partition[0][2]});
            double[] axisP22D = toViewScreenPoint(new double[]{partition[1][0], partition[1][1], partition[1][2]});
            if(axisP12D == null || axisP22D == null){continue;}
            StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
        }
        double[] axisP22D = toViewScreenPoint(new double[]{axis[1][0], axis[1][1], axis[1][2]});
        if(axisP22D == null){return;}
        StdDraw.setPenColor(colorScheme.labelColor);
        StdDraw.text(axisP22D[0], axisP22D[1], label);
    }
    @Override
    public void drawLabels(){
        StdDraw.setPenColor(colorScheme.labelColor);
        double numericMin = xAxisMin;
        double numericMax = xAxisMax;
        for(double numericX = numericMin-numericMin%labeler.getLabelIntervals()[0]; numericX <= numericMax; numericX += labeler.getLabelIntervals()[0]){
            if(Math.abs(numericX) < ZERO_TOLERANCE){continue;}
            double[] labelLocation = toViewScreenPoint(new double[]{numericX, 0, 0});
            if(labelLocation == null){continue;}
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
        }
        numericMin = yAxisMin;
        numericMax = yAxisMax;
        for(double numericY = numericMin-numericMin%labeler.getLabelIntervals()[1]; numericY <= numericMax; numericY += labeler.getLabelIntervals()[1]){
            if(Math.abs(numericY) < ZERO_TOLERANCE){continue;}
            double[] labelLocation = toViewScreenPoint(new double[]{0, numericY, 0});
            if(labelLocation == null){continue;}
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
        }
        numericMin = zAxisMin;
        numericMax = zAxisMax;
        for(double numericZ = numericMin-numericMin%labeler.getLabelIntervals()[2]; numericZ <= numericMax; numericZ += labeler.getLabelIntervals()[2]){
            if(Math.abs(numericZ) < ZERO_TOLERANCE){continue;}
            double[] labelLocation = toViewScreenPoint(new double[]{0, 0, numericZ});
            if(labelLocation == null){continue;}
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
        }
    }
}

