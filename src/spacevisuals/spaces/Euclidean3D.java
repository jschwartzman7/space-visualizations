package spacevisuals.spaces;

import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.functions.MatrixUtils;
import spacevisuals.spaces.helpers.Camera3D;

import edu.princeton.cs.introcs.StdDraw;
/*
 * adjust label interval as distortion changes, not just displayed value
 */
public class Euclidean3D extends AbstractSpace{

    private final double ROTATION_RATE = Math.PI/32;
    private double[][] xAxis;
    private double[][] yAxis;
    private double[][] zAxis;
    public double xAxisMin;
    public double xAxisMax;
    public double yAxisMin;
    public double yAxisMax;
    public double zAxisMin;
    public double zAxisMax;
    public MatrixUtils matrixUtils;
    public Camera3D camera;
    //public double[][] currentPosition;

    public Euclidean3D(int defaultScale, int defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 3, 8, 0.08);
        this.xAxisMin = -defaultScale;
        this.xAxisMax = defaultScale;
        this.yAxisMin = -defaultScale;
        this.yAxisMax = defaultScale;
        this.zAxisMin = -defaultScale;
        this.zAxisMax = defaultScale;
        setAxes();
        //this.matrixUtils = new MatrixUtils(ROTATION_RATE);
        this.camera = new Camera3D();
    }

    public double[] toDrawablePoint(double[] numericPoint){
        return camera.toDrawablePoint(numericPoint);
        /*double[] rotatedPoint = matrixUtils.matrixVectorRmxnRn_Rm(c, new double[]{numericPoint[0], numericPoint[1], numericPoint[2]});
        return new double[] {Math.sqrt(3)/2*(rotatedPoint[1]-rotatedPoint[0]), rotatedPoint[2] - .5*(rotatedPoint[1]+rotatedPoint[0])};*/
    }

    private void setAxes(){
        this.xAxis = new double[][]{{xAxisMin, 0, 0}, {xAxisMax, 0, 0}};
        this.yAxis = new double[][]{{0, yAxisMin, 0}, {0, yAxisMax, 0}};
        this.zAxis = new double[][]{{0, 0, zAxisMin}, {0, 0, zAxisMax}};
    }

    private void drawAxis(double[][] axis, Color color, String label){
        double[] axisP12D = toDrawablePoint(new double[]{axis[0][0], axis[0][1], axis[0][2]});
        double[] axisP22D = toDrawablePoint(new double[]{axis[1][0], axis[1][1], axis[1][2]});
		StdDraw.setPenColor(color);
        StdDraw.text(axisP22D[0], axisP22D[1], label);
        StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
    }

    public void drawAxes(){
        drawAxis(xAxis, Color.blue, "x");
        drawAxis(yAxis, Color.green, "y");
        drawAxis(zAxis, Color.red, "z");
        StdDraw.text(xMaxClip-.2*(xMaxClip-xMinClip), yMinClip+0.2*(yMaxClip-yMinClip), "focal length: "+camera.focalLength);
   
    }

    public void drawSpaceInfo(){
        StdDraw.setPenColor();
        double numericMin = xAxisMin;
        double numericMax = xAxisMax;
        for(double numericX = numericMin-numericMin%primaryLabelInterval; numericX <= numericMax; numericX += primaryLabelInterval){
            if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = toDrawablePoint(new double[]{numericX, 0, 0});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericX));
        }
        numericMin = yAxisMin;
        numericMax = yAxisMax;
        for(double numericY = numericMin-numericMin%primaryLabelInterval; numericY <= numericMax; numericY += primaryLabelInterval){
            if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = toDrawablePoint(new double[]{0, numericY, 0});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericY));
        }
        numericMin = zAxisMin;
        numericMax = zAxisMax;
        for(double numericZ = numericMin-numericMin%secondaryLabelInterval; numericZ <= numericMax; numericZ += secondaryLabelInterval){
            if(Math.abs(numericZ) < FLOAT_TOLERANCE){continue;}
            double[] labelLocation = toDrawablePoint(new double[]{0, 0, numericZ});
            StdDraw.text(labelLocation[0], labelLocation[1], toLabel(numericZ));
        }
    }

    public void updateLabels(){;
        //updateLabelIntervals(displayScale, displayScale);
    };

    public void updateView(){
        double primaryRange = xMaxClip-xMinClip;
        double primaryTranslationAmount = primaryRange*MOVE_SENSITIVITY;
        double primaryZoomAmount = primaryRange*MOVE_SENSITIVITY;
        double xyDistortionAmount = MOVE_SENSITIVITY;
        double zDistortionAmount = MOVE_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)){
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                camera.yaw += ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                camera.yaw -= ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                camera.roll += ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                camera.roll -= ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                camera.pitch += ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                camera.pitch -= ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                camera.focalLength *= (1.01);
            }
        }
        else{
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                camera.focalLength *= (0.99);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                camera.x += ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                camera.x -= ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                camera.y += ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                camera.y -= ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                camera.z += ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                camera.z -= ROTATION_RATE;
            }
            
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                zoomXClip(primaryZoomAmount);
                zoomYClip(primaryZoomAmount);
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                zoomXClip(-primaryZoomAmount);
                zoomYClip(-primaryZoomAmount);
            }
            /*if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                zoomXClip(-primaryZoomAmount);
                zoomYClip(-primaryZoomAmount);
                Z_MIN += secondaryZoomAmount;
                Z_MAX -= secondaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                zoomXClip(primaryZoomAmount);
                zoomYClip(primaryZoomAmount);
                Z_MIN -= secondaryZoomAmount;
                Z_MAX += secondaryZoomAmount;
            }*/
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
        }
        setAxes();
        setSpaceScale();
	}
}

