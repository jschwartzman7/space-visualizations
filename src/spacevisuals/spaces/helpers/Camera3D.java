package spacevisuals.spaces.helpers;

import spacevisuals.functions.MatrixUtils;

public class Camera3D {

    // rotations about each axis
    private final double DEFAULT_PITCH;
    public double pitch;
    public final double DEFAULT_ROLL;
    public double roll;
    public final double DEFAULT_YAW;
    public double yaw;
    // translations about each axis
    public final double DEFAULT_X;
    public double x;
    public final double DEFAULT_Y;
    public double y;
    public final double DEFAULT_Z;
    public double z;
    // distance from eye to view screen, used for projecting to clip space and perspective
    public final double DEFAULT_FOCAL_LENGTH;
    public double focalLength;

    public Camera3D(){
        this.DEFAULT_PITCH = Math.PI/4;
        this.pitch = DEFAULT_PITCH;
        this.DEFAULT_ROLL = 0;
        this.roll = DEFAULT_ROLL;
        this.DEFAULT_YAW = 0;
        this.yaw = DEFAULT_YAW;
        this.DEFAULT_X = 1;
        this.x = DEFAULT_X;
        this.DEFAULT_Y = 1;
        this.y = DEFAULT_Y;
        this.DEFAULT_Z = 1;
        this.z = DEFAULT_Z;
        this.DEFAULT_FOCAL_LENGTH = 10;
        this.focalLength = DEFAULT_FOCAL_LENGTH;
    }

    public Camera3D(double pitch, double roll, double yaw, double x, double y, double z, double focalLength){
        this.DEFAULT_PITCH = pitch;
        this.pitch = pitch;
        this.DEFAULT_ROLL = roll;
        this.roll = roll;
        this.DEFAULT_YAW = yaw;
        this.yaw = yaw;
        this.DEFAULT_X = x;
        this.x = x;
        this.DEFAULT_Y = y;
        this.y = y;
        this.DEFAULT_Z = z;
        this.z = z;
        this.DEFAULT_FOCAL_LENGTH = focalLength;
        this.focalLength = focalLength;
    }

    public double[] toDrawablePoint(double[] worldPoint){
        double[] distortedPoint = new double[]{worldPoint[0], worldPoint[1], worldPoint[2]};
        double[] cameraViewOrientedPoint = toCameraView(distortedPoint);
        double[] projectedPoint = projectPoint(cameraViewOrientedPoint);
        return projectedPoint;
    }


    private double[] toCameraView(double[] worldPoint){
        double[] homogenousPoint = new double[]{worldPoint[0], worldPoint[1], worldPoint[2], 1};
        double[] homogenousPointCameraView = MatrixUtils.matrixVectorRmxnRn_Rm(getCameraPosition(), homogenousPoint);
        return new double[]{homogenousPointCameraView[0], homogenousPointCameraView[1], homogenousPointCameraView[2]};
    }

    private double[][] getCameraPosition(){
        return new double[][]{{Math.cos(roll)*Math.cos(yaw), -Math.cos(roll)*Math.sin(yaw), -Math.sin(roll), x},     
                                             {Math.cos(pitch)*Math.sin(yaw)-Math.sin(pitch)*Math.sin(roll)*Math.cos(yaw), Math.sin(pitch)*Math.sin(roll)*Math.sin(yaw)+Math.cos(pitch)*Math.cos(yaw), -Math.sin(pitch)*Math.cos(roll), y},
                                             {Math.cos(pitch)*Math.sin(roll)*Math.cos(yaw)+Math.sin(pitch)*Math.sin(yaw), Math.sin(pitch)*Math.cos(yaw)-Math.cos(pitch)*Math.sin(roll)*Math.sin(yaw), Math.cos(pitch)*Math.cos(roll), z},
                                             {0, 0, 0, 1}};
    }
    /*private double[][] getCameraPosition(){
        return MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ4x4(roll), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.XZ4x4(pitch), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ4x4(yaw), new double[][]{{x}, {y}, {z}, {1}})));
    }*/

    private double[] projectPoint(double[] cameraViewPoint){
        double scaleFactor = focalLength/(focalLength+cameraViewPoint[2]);
        return new double[]{cameraViewPoint[0]*scaleFactor, cameraViewPoint[1]*scaleFactor};
    }

}
