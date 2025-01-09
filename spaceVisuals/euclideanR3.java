import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.LayoutFocusTraversalPolicy;

import edu.princeton.cs.introcs.StdDraw;
/*
 * adjust label interval as distortion changes, not just displayed value
 */
public class euclideanR3 extends abstractSpaceVisual{

    private final double ROTATION_RATE = Math.PI/32;
    private double displayScale;
    private double[][] xAxis;
    private double[][] yAxis;
    private double[][] zAxis;
    public double Z_MIN;
    public double Z_MAX;
    public double[][] currentPosition;
    public double xyDistortion;
    public double zDistortion;

    public euclideanR3(int defaultScale, int defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 3, 6, 0.08, 0.08);
        this.displayScale = defaultScale;
        this.Z_MIN = -defaultScale;
        this.Z_MAX = defaultScale;
        this.currentPosition = matrix.identity;
        this.xyDistortion = 1;
        this.zDistortion = 1;
        setAxes();
    }

	public double[] to2D(double[] cord) {
		return new double[] {Math.sqrt(3)/2*(cord[1]-cord[0]), cord[2] - .5*(cord[1]+cord[0])};
	}

    private void setAxes(){
        this.xAxis = new double[][]{{X_MIN, 0, 0}, {X_MAX, 0, 0}};
        this.yAxis = new double[][]{{0, Y_MIN, 0}, {0, Y_MAX, 0}};
        this.zAxis = new double[][]{{0, 0, Z_MIN}, {0, 0, Z_MAX}};
    }

    private void drawAxis(double[][] axis, Color color, String label){
        double[] axisP1 = matrix.matrixVectorMultiplication(currentPosition, axis[0]);
        double[] axisP2 = matrix.matrixVectorMultiplication(currentPosition, axis[1]);
        double[] axisP12D = to2D(axisP1);
        double[] axisP22D = to2D(axisP2);
        StdDraw.setPenColor();
        StdDraw.text(axisP22D[0], axisP22D[1], label);
		StdDraw.setPenColor(color);
        StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
    }

    public void drawSpace(){
        drawAxis(xAxis, Color.blue, "x");
        drawAxis(yAxis, Color.green, "y");
        drawAxis(zAxis, Color.red, "z");
        if(VIEW_SPACE_INFO){
            StdDraw.setPenColor();
            double numericXMin = X_MIN*xyDistortion;
            double numericXMax = X_MAX*xyDistortion;
            for(double numericX = numericXMin-numericXMin%primaryLabelInterval; numericX <= numericXMax; numericX += primaryLabelInterval){
                if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
                double[] labelLocation = to2D(matrix.matrixVectorMultiplication(currentPosition, new double[]{numericX/xyDistortion, 0, 0}));
                StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
            }
            double numericYMin = Y_MIN*xyDistortion;
            double numericYMax = Y_MAX*xyDistortion;
            for(double numericY = numericYMin-numericYMin%primaryLabelInterval; numericY <= numericYMax; numericY += primaryLabelInterval){
                if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
                double[] labelLocation = to2D(matrix.matrixVectorMultiplication(currentPosition, new double[]{0, numericY/xyDistortion, 0}));
                StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
            }
            double numericZMin = Z_MIN*zDistortion;
            double numericZMax = Z_MAX*zDistortion;
            for(double numericZ = numericZMin-numericZMin%secondaryLabelInterval; numericZ <= numericZMax; numericZ += secondaryLabelInterval){
                if(Math.abs(numericZ) < FLOAT_TOLERANCE){continue;}
                double[] labelLocation = to2D(matrix.matrixVectorMultiplication(currentPosition, new double[]{0, 0, numericZ/zDistortion}));
                StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
            }
        }
    }

    public void updateView(){
        double scalePrimaryIntervalRatio = displayScale*xyDistortion / primaryLabelInterval;
        if(scalePrimaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            primaryLabelInterval *= 2;
        }
        else if(scalePrimaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            primaryLabelInterval /= 2;
        }
        double scaleSecondaryIntervalRatio = displayScale*zDistortion / secondaryLabelInterval;
        if(scaleSecondaryIntervalRatio > RANGE_INTERVAL_RATIO_MAX){
            secondaryLabelInterval *= 2;
        }
        else if(scaleSecondaryIntervalRatio < RANGE_INTERVAL_RATIO_MIN){
            secondaryLabelInterval /= 2;
        }

        double xRange = X_MAX-X_MIN;
        double yRange = Y_MAX-Y_MIN;
        double zRange = Z_MAX-Z_MIN;
        double xTranslationAmount = xRange*TRANSLATION_SENSITIVITY;
        double yTranslationAmount = yRange*TRANSLATION_SENSITIVITY;
        double zTranslationAmount = zRange*TRANSLATION_SENSITIVITY;
        double xZoomAmount = xRange*ZOOM_SENSITIVITY;
        double yZoomAmount = yRange*ZOOM_SENSITIVITY;
        double zZoomAmount = zRange*ZOOM_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)){
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                translateX(xTranslationAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                translateX(-xTranslationAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                translateY(yTranslationAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                translateY(-yTranslationAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                Z_MIN += zTranslationAmount;
                Z_MAX += zTranslationAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                Z_MIN -= zTranslationAmount;
                Z_MAX -= zTranslationAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                zDistortion += zDistortion*ZOOM_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                zDistortion -= zDistortion*ZOOM_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                xyDistortion -= xyDistortion*ZOOM_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                xyDistortion += xyDistortion*ZOOM_SENSITIVITY;
            }
        }
        else{
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                currentPosition = matrix.matrixMatrixMultiplication(matrix.posXY(ROTATION_RATE), currentPosition);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                currentPosition = matrix.matrixMatrixMultiplication(matrix.negXY(ROTATION_RATE), currentPosition);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                currentPosition = matrix.matrixMatrixMultiplication(matrix.negYZ(ROTATION_RATE), currentPosition);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                currentPosition = matrix.matrixMatrixMultiplication(matrix.posYZ(ROTATION_RATE), currentPosition);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                currentPosition = matrix.matrixMatrixMultiplication(matrix.posXZ(ROTATION_RATE), currentPosition);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                currentPosition = matrix.matrixMatrixMultiplication(matrix.negXZ(ROTATION_RATE), currentPosition);
            }

            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                displayScale += displayScale*ZOOM_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                displayScale -= displayScale*ZOOM_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                zoomX(-xZoomAmount);
                zoomY(-yZoomAmount);
                Z_MIN += zZoomAmount;
                Z_MAX -= zZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                zoomX(xZoomAmount);
                zoomY(yZoomAmount);
                Z_MIN -= zZoomAmount;
                Z_MAX += zZoomAmount;
            }
        }
        
        
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
            Z_MIN = -DEFAULT_SCALE;
            Z_MAX = DEFAULT_SCALE;
            displayScale = DEFAULT_SCALE;
            currentPosition = matrix.identity;
            xyDistortion = 1;
            zDistortion = 1;
        }
        
        setAxes();
        StdDraw.setScale(-displayScale, displayScale);
	}
}

