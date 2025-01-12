import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.LayoutFocusTraversalPolicy;

import edu.princeton.cs.introcs.StdDraw;
/*
 * adjust label interval as distortion changes, not just displayed value
 */
public class Euclidean3D extends abstractSpaceVisual{

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

    public Euclidean3D(int defaultScale, int defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 3, 6, 0.08, 0.08);
        this.displayScale = defaultScale;
        this.Z_MIN = -defaultScale;
        this.Z_MAX = defaultScale;
        this.currentPosition = Matrix.identity;
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
        double[] axisP1 = Matrix.matrixVectorMultiplication(currentPosition, axis[0]);
        double[] axisP2 = Matrix.matrixVectorMultiplication(currentPosition, axis[1]);
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
            double numericMin = X_MIN*xyDistortion;
            double numericMax = X_MAX*xyDistortion;
            for(double numericX = numericMin-numericMin%primaryLabelInterval; numericX <= numericMax; numericX += primaryLabelInterval){
                if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
                double[] labelLocation = to2D(Matrix.matrixVectorMultiplication(currentPosition, new double[]{numericX/xyDistortion, 0, 0}));
                StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
            }
            numericMin = Y_MIN*xyDistortion;
            numericMax = Y_MAX*xyDistortion;
            for(double numericY = numericMin-numericMin%primaryLabelInterval; numericY <= numericMax; numericY += primaryLabelInterval){
                if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
                double[] labelLocation = to2D(Matrix.matrixVectorMultiplication(currentPosition, new double[]{0, numericY/xyDistortion, 0}));
                StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
            }
            numericMin = Z_MIN*zDistortion;
            numericMax = Z_MAX*zDistortion;
            for(double numericZ = numericMin-numericMin%secondaryLabelInterval; numericZ <= numericMax; numericZ += secondaryLabelInterval){
                if(Math.abs(numericZ) < FLOAT_TOLERANCE){continue;}
                double[] labelLocation = to2D(Matrix.matrixVectorMultiplication(currentPosition, new double[]{0, 0, numericZ/zDistortion}));
                StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
            }
        }
    }

    public void updateView(){
        double scalePrimaryIntervalRatio = displayScale*xyDistortion / primaryLabelInterval;
        double scaleSecondaryIntervalRatio = displayScale*zDistortion / secondaryLabelInterval;
        updateLabelIntervals(scalePrimaryIntervalRatio, scaleSecondaryIntervalRatio);
        double range = X_MAX-X_MIN;
        double translationAmount = range*TRANSLATION_SENSITIVITY;
        double zoomAmount = range*ZOOM_SENSITIVITY;
        double xyDistortionAmount = xyDistortion*ZOOM_SENSITIVITY;
        double zDistortionAmount = zDistortion*ZOOM_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)){
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                translateX(translationAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                translateX(-translationAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                translateY(translationAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                translateY(-translationAmount);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                Z_MIN += translationAmount;
                Z_MAX += translationAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                Z_MIN -= translationAmount;
                Z_MAX -= translationAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                zDistortion -= zDistortionAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                zDistortion += zDistortionAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                xyDistortion -= xyDistortionAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                xyDistortion += xyDistortionAmount;
            }
        }
        else{
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                currentPosition = Matrix.matrixMatrixMultiplication(Matrix.posXY(ROTATION_RATE), currentPosition);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                currentPosition = Matrix.matrixMatrixMultiplication(Matrix.negXY(ROTATION_RATE), currentPosition);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                currentPosition = Matrix.matrixMatrixMultiplication(Matrix.negYZ(ROTATION_RATE), currentPosition);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                currentPosition = Matrix.matrixMatrixMultiplication(Matrix.posYZ(ROTATION_RATE), currentPosition);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                currentPosition = Matrix.matrixMatrixMultiplication(Matrix.posXZ(ROTATION_RATE), currentPosition);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                currentPosition = Matrix.matrixMatrixMultiplication(Matrix.negXZ(ROTATION_RATE), currentPosition);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                displayScale += displayScale*ZOOM_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                displayScale -= displayScale*ZOOM_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                zoomX(-zoomAmount);
                zoomY(-zoomAmount);
                Z_MIN += zoomAmount;
                Z_MAX -= zoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                zoomX(zoomAmount);
                zoomY(zoomAmount);
                Z_MIN -= zoomAmount;
                Z_MAX += zoomAmount;
            }
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
            Z_MIN = -DEFAULT_SCALE;
            Z_MAX = DEFAULT_SCALE;
            displayScale = DEFAULT_SCALE;
            currentPosition = Matrix.identity;
            xyDistortion = 1;
            zDistortion = 1;
        }
        setAxes();
        StdDraw.setScale(-displayScale, displayScale);
	}
}

