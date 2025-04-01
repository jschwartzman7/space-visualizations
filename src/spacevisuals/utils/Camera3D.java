package spacevisuals.utils;

import spacevisuals.functions.Matrix3D;

public class Camera3D{

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

    public Camera3D(){
        this.DEFAULT_PITCH = Constants.CAMERA3D_PITCH;
        this.DEFAULT_ROLL = Constants.CAMERA3D_ROLL;
        this.DEFAULT_YAW = Constants.CAMERA3D_YAW;
        this.DEFAULT_X = Constants.CAMERA3D_X;
        this.DEFAULT_Y = Constants.CAMERA3D_Y;
        this.DEFAULT_Z = Constants.CAMERA3D_Z;
        this.DEFAULT_FOCAL_LENGTH = Constants.CAMERA3D_FOCAL_LENGTH;
        setVariables();
    }

    public Camera3D(double pitch, double roll, double yaw, double x, double y, double z, double focalLength){
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

    public double[] toCameraPosition(double[] worldPoint){
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
}