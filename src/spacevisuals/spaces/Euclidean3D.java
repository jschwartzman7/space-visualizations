package spacevisuals.spaces;

import java.awt.Color;

import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.functions.Matrix3D;
import spacevisuals.helpers.*;
import spacevisuals.spaces.intervalranges.AxisIntervals3D;
import spacevisuals.spaces.spacemovers.SpaceMover;
import spacevisuals.spaces.spacemovers.SpaceMover3D;
import edu.princeton.cs.introcs.StdDraw;
/*
 * adjust label interval as distortion changes, not just displayed value
 */
public class Euclidean3D extends AbstractSpace{

    public final double ROTATION_RATE = Math.PI/32;
    public double xAxisMin;
    public double xAxisMax;
    public double yAxisMin;
    public double yAxisMax;
    public double zAxisMin;
    public double zAxisMax;
    public Matrix3D matrixUtils;
    public Camera3DSpace camera;
    public SpaceMover mover;
    public AxisIntervals3D labeler;
    private static SingletonSpace<Euclidean3D> spaceSingleton = new SingletonSpace<Euclidean3D>();
    private Euclidean3D(){
        super();
        initializeVariables(DEFAULT_CLIP_SCALE);
    }
    private Euclidean3D(boolean viewSpaceInfo){
        super(viewSpaceInfo);
        initializeVariables(DEFAULT_CLIP_SCALE);
    }
    private Euclidean3D(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        super(defaultScale, moveSensitivity, viewSpaceInfo);
        initializeVariables(defaultScale);
    }
    public static Euclidean3D Get(boolean viewSpaceInfo){
        return spaceSingleton.getOrCreateSpace(() -> new Euclidean3D(viewSpaceInfo));
    }
    public static Euclidean3D Get(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        return spaceSingleton.getOrCreateSpace(() -> new Euclidean3D(defaultScale, moveSensitivity, viewSpaceInfo));
    }
    public static Euclidean3D Get(){
        return spaceSingleton.getOrCreateSpace(() -> new Euclidean3D());
    }

    public void initializeVariables(double scale){
        this.xAxisMin = -scale;
        this.xAxisMax = scale;
        this.yAxisMin = -scale;
        this.yAxisMax = scale;
        this.zAxisMin = -scale;
        this.zAxisMax = scale;
        this.camera = new Camera3DSpace();
        initializeMover();
        initializeLabeler();
        initializeColorScheme();
        this.dimensions = 3;
    }

    public void initializeMover(){
        this.mover = new SpaceMover3D(this);
    }
    public void initializeLabeler(){
        this.labeler = new AxisIntervals3D(this, DEFAULT_CLIP_SCALE, 3, 8);
    }
    public void initializeColorScheme(){
        this.colorScheme = SpaceColorScheme.from("red");
    }
    public double[] toViewScreenPoint(double[] numericPoint){
        return camera.toDrawablePoint(numericPoint);
        /*double[] rotatedPoint = matrixUtils.matrixVectorRmxnRn_Rm(c, new double[]{numericPoint[0], numericPoint[1], numericPoint[2]});
        return new double[] {Math.sqrt(3)/2*(rotatedPoint[1]-rotatedPoint[0]), rotatedPoint[2] - .5*(rotatedPoint[1]+rotatedPoint[0])};*/
    }

    private double[][][] partitionAxis(double[][] axis, String label){
        int numPartitions = Math.max(2, 20);
        double[][][] partitionedAxis = new double[numPartitions][2][3];
        switch(label){
            case "x":
                double partitionLength = (axis[1][0]-axis[0][0])/numPartitions;
                for(int i = 0; i < numPartitions; i++){
                    partitionedAxis[i][0] = new double[]{axis[0][0]+i*partitionLength, axis[0][1], axis[0][2]};
                    partitionedAxis[i][1] = new double[]{axis[0][0]+(i+1)*partitionLength, axis[0][1], axis[0][2]};
                }
                break;
            case "y":
                partitionLength = (axis[1][1]-axis[0][1])/numPartitions;
                for(int i = 0; i < numPartitions; i++){
                    partitionedAxis[i][0] = new double[]{axis[0][0], axis[0][1]+i*partitionLength, axis[0][2]};
                    partitionedAxis[i][1] = new double[]{axis[0][0], axis[0][1]+(i+1)*partitionLength, axis[0][2]};
                }
                break;
            case "z":
                partitionLength = (axis[1][2]-axis[0][2])/numPartitions;
                for(int i = 0; i < numPartitions; i++){
                    partitionedAxis[i][0] = new double[]{axis[0][0], axis[0][1], axis[0][2]+i*partitionLength};
                    partitionedAxis[i][1] = new double[]{axis[0][0], axis[0][1], axis[0][2]+(i+1)*partitionLength};
                }
                break;
        }
        return partitionedAxis;
    }

    private void drawAxis(double[][] axis, Color color, String label){
        
        double[][][] partitionedAxis = partitionAxis(axis, label);
        for(double[][] partition : partitionedAxis){
            double[] axisP12D = toViewScreenPoint(new double[]{partition[0][0], partition[0][1], partition[0][2]});
            double[] axisP22D = toViewScreenPoint(new double[]{partition[1][0], partition[1][1], partition[1][2]});
            StdDraw.setPenColor(color);
            //StdDraw.text(axisP22D[0], axisP22D[1], label);
            StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
        }
        double[] axisP22D = toViewScreenPoint(new double[]{axis[1][0], axis[1][1], axis[1][2]});
        StdDraw.setPenColor(color);
        //StdDraw.text(axisP22D[0], axisP22D[1], label);
        StdDraw.text(axisP22D[0], axisP22D[1], label);
    }

    public void drawAxes(){
        drawAxis(new double[][]{{xAxisMin, 0, 0}, {xAxisMax, 0, 0}}, colorScheme.xAxisColor, "x");
        drawAxis(new double[][]{{0, yAxisMin, 0}, {0, yAxisMax, 0}}, colorScheme.yAxisColor, "y");
        drawAxis(new double[][]{{0, 0, zAxisMin}, {0, 0, zAxisMax}}, colorScheme.zAxisColor, "z");
    }

    public void drawLabels(){
        StdDraw.setPenColor(colorScheme.labelColor);
        double numericMin = xAxisMin;
        double numericMax = xAxisMax;
        for(double numericX = numericMin-numericMin%labeler.labeler.labelIntervals[0]; numericX <= numericMax; numericX += labeler.labeler.labelIntervals[0]){
            if(Math.abs(numericX) < ZERO_TOLERANCE){continue;}
            double[] labelLocation = toViewScreenPoint(new double[]{numericX, 0, 0});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
        }
        numericMin = yAxisMin;
        numericMax = yAxisMax;
        for(double numericY = numericMin-numericMin%labeler.labeler.labelIntervals[1]; numericY <= numericMax; numericY += labeler.labeler.labelIntervals[1]){
            if(Math.abs(numericY) < ZERO_TOLERANCE){continue;}
            double[] labelLocation = toViewScreenPoint(new double[]{0, numericY, 0});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
        }
        numericMin = zAxisMin;
        numericMax = zAxisMax;
        for(double numericZ = numericMin-numericMin%labeler.labeler.labelIntervals[2]; numericZ <= numericMax; numericZ += labeler.labeler.labelIntervals[2]){
            if(Math.abs(numericZ) < ZERO_TOLERANCE){continue;}
            double[] labelLocation = toViewScreenPoint(new double[]{0, 0, numericZ});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
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

