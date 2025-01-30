package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.functions.MatrixUtils;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class Perspective3D extends AbstractSpace{


    private final double DEFAULT_ROTATION_RATE = Math.PI/32;
    private final double DEFAULT_FOCAL_LENGTH = 5;
    private double[][] xAxis;
    private double[][] yAxis;
    private double[][] zAxis;
    public double displayScale;
    public double Z_MIN;
    public double Z_MAX;
    public MatrixUtils matrixUtils;
    public double[][] currentPosition;
    private double[][] inverseCameraPosition;
    private double focalLength;

    public Perspective3D(int defaultScale, int defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 3, 6, 0.08, 0.08);
        this.displayScale = defaultScale;
        this.Z_MIN = -defaultScale;
        this.Z_MAX = defaultScale;
        this.matrixUtils = new MatrixUtils(DEFAULT_ROTATION_RATE);
        this.currentPosition = matrixUtils.identity;
        this.inverseCameraPosition = matrixUtils.identity;
        this.focalLength = DEFAULT_FOCAL_LENGTH;
        setAxes();
    }

    private List<double[][]> partitionAxis(double[][] axis, int numPartitions, String label){
        List<double[][]> linePointPairs = new LinkedList<double[][]>();
        numPartitions = Math.max(numPartitions, 1);
        double increment;
        switch (label) {
            case "x":
                increment = (axis[0][0]-axis[1][0])/numPartitions;
                for(int i = 0; i < numPartitions; i++){
                    linePointPairs.add(new double[][]{{axis[0][0]+ (i)*increment, 0, 0}, {axis[0][0] + (i+1)*increment, 0, 0}});
                }
                break;
            case "y":
                increment = (axis[0][1]-axis[1][1])/numPartitions;
                for(int i = 0; i < numPartitions; i++){
                    linePointPairs.add(new double[][]{{0, axis[0][1]+ (i)*increment, 0}, {0, axis[0][1] + (i+1)*increment, 0}});
                }
                break;
            case "z":
                increment = (axis[0][2]-axis[1][2])/numPartitions;
                for(int i = 0; i < numPartitions; i++){
                    linePointPairs.add(new double[][]{{0, 0, axis[0][2]+ (i)*increment}, {0, 0, axis[0][2] + (i+1)*increment}});
                }
                break;
            default:
                break;
        }
        return linePointPairs;
    }

    /*
    public double[] toDrawablePoint(double[] numericPoint){
        double[] rotatedPoint = matrixUtils.matrixVectorRmxnRn_Rm(currentPosition, new double[]{numericPoint[0]/primaryDistortion, numericPoint[1]/primaryDistortion, numericPoint[2]/secondaryDistortion});
        return new double[] {Math.sqrt(3)/2*(rotatedPoint[1]-rotatedPoint[0]), rotatedPoint[2] - .5*(rotatedPoint[1]+rotatedPoint[0])};
    }
     */

    public double[] toDrawablePoint(double[] numericPoint){
        double[] distortedPoint = new double[]{numericPoint[0]/primaryDistortion, numericPoint[1]/primaryDistortion, numericPoint[2]/secondaryDistortion};
        double[] cameraViewOrientedPoint = matrixUtils.matrixVectorRmxnRn_Rm(inverseCameraPosition, distortedPoint);
        double[] projectedPoint = projectPoint(cameraViewOrientedPoint);
        return projectedPoint;
    }

    public double[] projectPoint(double[] cameraViewPoint){
        double scaleFactor = focalLength/(focalLength+cameraViewPoint[2]);
        return new double[]{cameraViewPoint[0]*scaleFactor, cameraViewPoint[1]*scaleFactor};
    }

    private void setAxes(){
        this.xAxis = new double[][]{{X_MIN, 0, 0}, {X_MAX, 0, 0}};
        this.yAxis = new double[][]{{0, Y_MIN, 0}, {0, Y_MAX, 0}};
        this.zAxis = new double[][]{{0, 0, Z_MIN}, {0, 0, Z_MAX}};
    }

    private void drawAxis(double[][] axis, Color color, String label){
        for(double[][] linePointPair: partitionAxis(axis, (int)(5.0/focalLength), label)){
            double[] axisP12D = toDrawablePoint(new double[]{linePointPair[0][0]*primaryDistortion, linePointPair[0][1]*primaryDistortion, linePointPair[0][2]*secondaryDistortion});
            double[] axisP22D = toDrawablePoint(new double[]{linePointPair[1][0]*primaryDistortion, linePointPair[1][1]*primaryDistortion, linePointPair[1][2]*secondaryDistortion});
            StdDraw.setPenColor(color);
            //StdDraw.text(axisP22D[0], axisP22D[1], label);
            StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
        }
        
    }

    public void drawAxes(){
        drawAxis(xAxis, Color.blue, "x");
        drawAxis(yAxis, Color.green, "y");
        drawAxis(zAxis, Color.red, "z");
    }

    public void drawSpaceInfo(){
        StdDraw.setPenColor();
        double numericMin = X_MIN*primaryDistortion;
        double numericMax = X_MAX*primaryDistortion;
        for(double numericX = numericMin-numericMin%primaryLabelInterval; numericX <= numericMax; numericX += primaryLabelInterval){
            if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = toDrawablePoint(new double[]{numericX, 0, 0});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
        }
        numericMin = Y_MIN*primaryDistortion;
        numericMax = Y_MAX*primaryDistortion;
        for(double numericY = numericMin-numericMin%primaryLabelInterval; numericY <= numericMax; numericY += primaryLabelInterval){
            if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = toDrawablePoint(new double[]{0, numericY, 0});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
        }
        numericMin = Z_MIN*secondaryDistortion;
        numericMax = Z_MAX*secondaryDistortion;
        for(double numericZ = numericMin-numericMin%secondaryLabelInterval; numericZ <= numericMax; numericZ += secondaryLabelInterval){
            if(Math.abs(numericZ) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = toDrawablePoint(new double[]{0, 0, numericZ});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
        }
        StdDraw.text(0.8*X_MAX, -0.9*Y_MAX, "focal length: "+focalLength);
    }

    public void updateLabels(){;
        updateLabelIntervals(displayScale, displayScale);
    };


    public void updateView(){
        double primaryRange = X_MAX-X_MIN;
        double secondaryRange = Z_MAX-Z_MIN;
        double primaryTranslationAmount = primaryRange*TRANSLATION_SENSITIVITY;
        double secondaryTranslationAmount = secondaryRange*TRANSLATION_SENSITIVITY;
        double primaryZoomAmount = primaryRange*ZOOM_SENSITIVITY;
        double secondaryZoomAmount = secondaryRange*ZOOM_SENSITIVITY;
        double xyDistortionAmount = primaryDistortion*ZOOM_SENSITIVITY;
        double zDistortionAmount = secondaryDistortion*ZOOM_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)){
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                translateX(primaryTranslationAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                translateX(-primaryTranslationAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                translateY(primaryTranslationAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                translateY(-primaryTranslationAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                Z_MIN += secondaryTranslationAmount;
                Z_MAX += secondaryTranslationAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                Z_MIN -= secondaryTranslationAmount;
                Z_MAX -= secondaryTranslationAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                adjustSecondaryDistortion(-zDistortionAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                adjustSecondaryDistortion(zDistortionAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                adjustPrimaryDistortion(-xyDistortionAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                adjustPrimaryDistortion(xyDistortionAmount);
            }
        }
        else{
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.posXY());
                inverseCameraPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(inverseCameraPosition, matrixUtils.negXY());
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.negXY());
                inverseCameraPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(inverseCameraPosition, matrixUtils.posXY());
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.negYZ());
                inverseCameraPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(inverseCameraPosition, matrixUtils.posYZ());
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.posYZ());
                inverseCameraPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(inverseCameraPosition, matrixUtils.negYZ());
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.posXZ());
                inverseCameraPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(inverseCameraPosition, matrixUtils.negXZ());
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.negXZ());
                inverseCameraPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(inverseCameraPosition, matrixUtils.posXZ());
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                focalLength *= 1.05;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_T)){
                focalLength *= 0.95;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                displayScale += displayScale*ZOOM_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                displayScale -= displayScale*ZOOM_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                zoomX(-primaryZoomAmount);
                zoomY(-primaryZoomAmount);
                Z_MIN += secondaryZoomAmount;
                Z_MAX -= secondaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                zoomX(primaryZoomAmount);
                zoomY(primaryZoomAmount);
                Z_MIN -= secondaryZoomAmount;
                Z_MAX += secondaryZoomAmount;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
            Z_MIN = -DEFAULT_SCALE;
            Z_MAX = DEFAULT_SCALE;
            displayScale = DEFAULT_SCALE;
            currentPosition = matrixUtils.identity;
            inverseCameraPosition = matrixUtils.identity;
            this.focalLength = DEFAULT_FOCAL_LENGTH;
        }
        setAxes();
        StdDraw.setScale(-displayScale, displayScale);
	}
}

