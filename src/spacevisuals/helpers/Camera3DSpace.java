package spacevisuals.helpers;

import spacevisuals.functions.MatrixUtils;

public class Camera3DSpace {

    private final double DEFAULT_PITCH;
    public double pitch;
    public final double DEFAULT_ROLL;
    public double roll;
    public final double DEFAULT_YAW;
    public double yaw;
    public final double DEFAULT_X;
    public double x;
    public final double DEFAULT_Y;
    public double y;
    public final double DEFAULT_Z;
    public double z;
    // distance from eye to view screen, used for projecting to clip space and perspective
    public final double DEFAULT_FOCAL_LENGTH;
    public double focalLength;

    public Camera3DSpace(){
        this.DEFAULT_PITCH = -Math.PI/3;
        this.pitch = DEFAULT_PITCH;
        this.DEFAULT_ROLL = Math.PI/3;
        this.roll = DEFAULT_ROLL;
        this.DEFAULT_YAW = Math.PI/3;
        this.yaw = DEFAULT_YAW;
        this.DEFAULT_X = 10;
        this.x = DEFAULT_X;
        this.DEFAULT_Y = 10;
        this.y = DEFAULT_Y;
        this.DEFAULT_Z = 10;
        this.z = DEFAULT_Z;
        this.DEFAULT_FOCAL_LENGTH = 3;
        this.focalLength = DEFAULT_FOCAL_LENGTH;
    }

    public Camera3DSpace(double pitch, double roll, double yaw, double x, double y, double z, double focalLength){
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
        double[] cameraViewOrientedPoint = toCameraPosition(worldPoint);
        double[] projectedPoint = projectPoint(cameraViewOrientedPoint);
        return new double[]{projectedPoint[0], projectedPoint[1]};
    }

    private double[] toCameraPosition(double[] worldPoint){
        worldPoint[0] -= x;
        worldPoint[1] -= y;
        worldPoint[2] -= z;
        worldPoint = MatrixUtils.matrixVectorRmxnRn_Rm(getCameraPosition(), worldPoint);
        //worldPoint = MatrixUtils.matrixVectorRmxnRn_Rm(MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ3x3(-yaw), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.XZ3x3(-roll), MatrixUtils.XY3x3(-pitch))), worldPoint);
        return worldPoint;
    }


    private double[][] getCameraPosition(){
        return new double[][]{{Math.cos(-pitch)*Math.cos(-roll), -Math.cos(roll)*Math.sin(pitch), -Math.sin(-roll)},     
        {Math.cos(-yaw)*Math.sin(-pitch)-Math.sin(-yaw)*Math.sin(-roll)*Math.cos(-pitch), Math.sin(-pitch)*Math.sin(-roll)*Math.sin(-yaw)+Math.cos(-pitch)*Math.cos(yaw), -Math.sin(-yaw)*Math.cos(-roll)},
        {Math.cos(-pitch)*Math.sin(-roll)*Math.cos(-yaw)+Math.sin(-pitch)*Math.sin(-yaw), Math.sin(-yaw)*Math.cos(-pitch)-Math.cos(-yaw)*Math.sin(-roll)*Math.sin(-pitch), Math.cos(-roll)*Math.cos(-yaw)}};
    }
    /*private double[][] getCameraPosition(){
        return MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ4x4(roll), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.XZ4x4(pitch), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ4x4(yaw), new double[][]{{x}, {y}, {z}, {1}})));
    }*/

    private double[] projectPoint(double[] cameraViewPoint){
        if(cameraViewPoint[2] < focalLength){
            System.out.println("Point is eclipsing camera.");
            return new double[]{cameraViewPoint[0], cameraViewPoint[1]};
            
        }
        double scaleFactor = focalLength/cameraViewPoint[2];
        return new double[]{cameraViewPoint[0]*scaleFactor, cameraViewPoint[1]*scaleFactor};
    
    }

}
