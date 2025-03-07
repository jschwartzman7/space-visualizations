package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;
import spacevisuals.functions.Matrix3D;

public class SpaceMover3DCamera extends SpaceUser<Euclidean3D> implements SpaceMover{

    public static final double MOVE_SENSITIVITY = 0.07;
    private final double DEFAULT_PITCH;
    public final double DEFAULT_ROLL;
    public final double DEFAULT_YAW;
    public final double DEFAULT_X;
    public final double DEFAULT_Y;
    public final double DEFAULT_Z;
    public final double DEFAULT_FOCAL_LENGTH;
    public double pitch;
    public double roll;
    public double yaw;
    public double x;
    public double y;
    public double z;
    public double focalLength;

    public SpaceMover3DCamera(){
        super(Euclidean3D.Get());
        this.DEFAULT_PITCH = 0;
        this.DEFAULT_ROLL = 0;
        this.DEFAULT_YAW = 0;
        this.DEFAULT_X = 0;
        this.DEFAULT_Y = 0;
        this.DEFAULT_Z = -50;
        this.DEFAULT_FOCAL_LENGTH = 25;
        setVariables();
    }
    public SpaceMover3DCamera(Euclidean3D space){
        super(space);
        this.DEFAULT_PITCH = 0;
        this.DEFAULT_ROLL = 0;
        this.DEFAULT_YAW = 0;
        this.DEFAULT_X = 0;
        this.DEFAULT_Y = 0;
        this.DEFAULT_Z = -50;
        this.DEFAULT_FOCAL_LENGTH = 25;
        setVariables();
    }

    public SpaceMover3DCamera(double pitch, double roll, double yaw, double x, double y, double z, double focalLength){
        super(Euclidean3D.Get());
        this.DEFAULT_PITCH = pitch;
        this.DEFAULT_ROLL = roll;
        this.DEFAULT_YAW = yaw;
        this.DEFAULT_X = x;
        this.DEFAULT_Y = y;
        this.DEFAULT_Z = z;
        this.DEFAULT_FOCAL_LENGTH = focalLength;
        setVariables();
    }

    public void setVariables(){
        this.pitch = DEFAULT_PITCH;
        this.roll = DEFAULT_ROLL;
        this.yaw = DEFAULT_YAW;
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.z = DEFAULT_Z;
        this.focalLength = DEFAULT_FOCAL_LENGTH;
    }

    public double[] toDrawablePoint(double[] worldPoint){
        double[] cameraViewOrientedPoint = toCameraPosition(worldPoint);
        double[] projectedPoint = projectPoint(cameraViewOrientedPoint);
        return projectedPoint;
    }

    private double[] toCameraPosition(double[] worldPoint){
        double[] rotatedPoint = Matrix3D.matrixVectorRmxnRn_Rm(Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix3D.YZ(-yaw), Matrix3D.XZ(-roll)), Matrix3D.XY(-pitch)), worldPoint);
        double[] translatedPoint = new double[]{rotatedPoint[0]-x, rotatedPoint[1]-y, rotatedPoint[2]-z};
        return translatedPoint;
    }

    private double[] projectPoint(double[] cameraViewPoint){
        if(cameraViewPoint[2] < focalLength){
            return null;
        }
        double scaleFactor = focalLength/cameraViewPoint[2];
        return new double[]{cameraViewPoint[0]*scaleFactor, cameraViewPoint[1]*scaleFactor};
    
    }

    public void resetView(){
        space.xAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        space.xAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;;
        space.yAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        space.yAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;
        space.zAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        space.zAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;
        space.resetClipScale();
        setVariables();
    }

    public void updateView(){
        double primaryRange = space.xAxisMin-space.xAxisMax;
        double primaryMoveAmount = primaryRange*space.MOVE_SENSITIVITY;
        double secondaryRange = space.zAxisMin-space.zAxisMax;
        double secondaryMoveAmount = secondaryRange*space.MOVE_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)){
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                this.x += primaryMoveAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                this.x -= primaryMoveAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                this.y += secondaryMoveAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                this.y -= secondaryMoveAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                this.focalLength /= (1+MOVE_SENSITIVITY/2);
            }
        }
        else{
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                this.pitch += MOVE_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                this.pitch -= MOVE_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                this.roll += MOVE_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                this.roll -= MOVE_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                this.yaw += MOVE_SENSITIVITY;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                this.yaw -= MOVE_SENSITIVITY;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                this.focalLength *= (1+MOVE_SENSITIVITY/2);
            }
            
            
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                space.zAxisMin -= secondaryMoveAmount;
                space.zAxisMax += secondaryMoveAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                space.zAxisMin += secondaryMoveAmount;
                space.zAxisMax -= secondaryMoveAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                space.xAxisMin += primaryMoveAmount;
                space.xAxisMax -= primaryMoveAmount;
                space.yAxisMin += primaryMoveAmount;
                space.yAxisMax -= primaryMoveAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                space.xAxisMin -= primaryMoveAmount;
                space.xAxisMax += primaryMoveAmount;
                space.yAxisMin -= primaryMoveAmount;
                space.yAxisMax += primaryMoveAmount;
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
                resetView();
            }
        }
    }
    
}
