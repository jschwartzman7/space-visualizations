package spacevisuals.spaces;

import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.functions.MatrixUtils;

import edu.princeton.cs.introcs.StdDraw;
/*
 * adjust label interval as distortion changes, not just displayed value
 */
public class Euclidean3D extends AbstractSpace{

    private final double ROTATION_RATE = Math.PI/32;
    private double[][] xAxis;
    private double[][] yAxis;
    private double[][] zAxis;
    public double displayScale;
    public double Z_MIN;
    public double Z_MAX;
    public MatrixUtils matrixUtils;
    public double[][] currentPosition;

    public Euclidean3D(int defaultScale, int defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 3, 6, 0.08, 0.08);
        this.displayScale = defaultScale;
        this.Z_MIN = -defaultScale;
        this.Z_MAX = defaultScale;
        this.matrixUtils = new MatrixUtils(Math.PI/32);
        this.currentPosition = matrixUtils.identity;
        setAxes();
    }

    public double[] toDrawablePoint(double[] numericPoint){
        double[] distortedPoint = new double[]{numericPoint[0]/primaryDistortion, numericPoint[1]/primaryDistortion, numericPoint[2]/secondaryDistortion};
        double[] rotatedPoint = matrixUtils.matrixVectorRnxmRn_Rm(currentPosition, distortedPoint);
        double[] point2D = to2D(rotatedPoint);
        return point2D;
    }

	private double[] to2D(double[] cord) {
		return new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), cord[2] - .5*(cord[1]+cord[0])};
	}

    private void setAxes(){
        this.xAxis = new double[][]{{X_MIN, 0, 0}, {X_MAX, 0, 0}};
        this.yAxis = new double[][]{{0, Y_MIN, 0}, {0, Y_MAX, 0}};
        this.zAxis = new double[][]{{0, 0, Z_MIN}, {0, 0, Z_MAX}};
    }

    private void drawAxis(double[][] axis, Color color, String label){
        double[] axisP1 = matrixUtils.matrixVectorRnxmRn_Rm(currentPosition, axis[0]);
        double[] axisP2 = matrixUtils.matrixVectorRnxmRn_Rm(currentPosition, axis[1]);
        double[] axisP12D = to2D(axisP1);
        double[] axisP22D = to2D(axisP2);
		StdDraw.setPenColor(color);
        StdDraw.text(axisP22D[0], axisP22D[1], label);
        StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
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
            double[] labelLocation = to2D(matrixUtils.matrixVectorRnxmRn_Rm(currentPosition, new double[]{numericX/primaryDistortion, 0, 0}));
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
        }
        numericMin = Y_MIN*primaryDistortion;
        numericMax = Y_MAX*primaryDistortion;
        for(double numericY = numericMin-numericMin%primaryLabelInterval; numericY <= numericMax; numericY += primaryLabelInterval){
            if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = to2D(matrixUtils.matrixVectorRnxmRn_Rm(currentPosition, new double[]{0, numericY/primaryDistortion, 0}));
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
        }
        numericMin = Z_MIN*secondaryDistortion;
        numericMax = Z_MAX*secondaryDistortion;
        for(double numericZ = numericMin-numericMin%secondaryLabelInterval; numericZ <= numericMax; numericZ += secondaryLabelInterval){
            if(Math.abs(numericZ) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = to2D(matrixUtils.matrixVectorRnxmRn_Rm(currentPosition, new double[]{0, 0, numericZ/secondaryDistortion}));
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
        }
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
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.negXY());
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.negYZ());
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.posYZ());
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.posXZ());
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                currentPosition = matrixUtils.matrixMatrixRmxnRnxp_Rmxp(currentPosition, matrixUtils.negXZ());
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
        }
        setAxes();
        StdDraw.setScale(-displayScale, displayScale);
	}
}

