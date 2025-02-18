package spacevisuals.helpers;

import spacevisuals.functions.Matrix3D;
import spacevisuals.spaces.AbstractSpace;

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
        this.DEFAULT_PITCH = 0;
        this.pitch = DEFAULT_PITCH;
        this.DEFAULT_ROLL = 0;
        this.roll = DEFAULT_ROLL;
        this.DEFAULT_YAW = 0;
        this.yaw = DEFAULT_YAW;
        this.DEFAULT_X = 0;
        this.x = DEFAULT_X;
        this.DEFAULT_Y = 0;
        this.y = DEFAULT_Y;
        this.DEFAULT_Z = 0;
        this.z = DEFAULT_Z;
        this.DEFAULT_FOCAL_LENGTH = 5;
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

    public void drawInfoTextBox(AbstractSpace space){
        TextBox textBox = new TextBox(space);
        textBox.addText("pitch", String.valueOf(pitch));
        textBox.addText("roll", String.valueOf(roll));
        textBox.addText("yaw", String.valueOf(yaw));
        textBox.addText("x", String.valueOf(x));
        textBox.addText("y", String.valueOf(y));
        textBox.addText("z", String.valueOf(z));
        textBox.addText("focal length", String.valueOf(focalLength));
        textBox.drawTextBox();
    }

    public double[] toDrawablePoint(double[] worldPoint){
        double[] cameraViewOrientedPoint = toCameraPosition(worldPoint);
        double[] projectedPoint = projectPoint(cameraViewOrientedPoint);
        if(projectedPoint == null){
            return new double[]{0, 0};
        }
        return new double[]{projectedPoint[0], projectedPoint[1]};
    }

    private double[] toCameraPosition(double[] worldPoint){
        double[] rotatedPoint = Matrix3D.matrixVectorRmxnRn_Rm(Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix3D.YZ(-yaw), Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix3D.XZ(-roll), Matrix3D.XY(-pitch))), worldPoint);
        double[] translatedPoint = new double[]{rotatedPoint[0]-x, rotatedPoint[1]-y, rotatedPoint[2]-z};
        //worldPoint = MatrixUtils.matrixVectorRmxnRn_Rm(getCameraPosition(), worldPoint);
        return translatedPoint;
    }

    public void resetCamera(){
        this.pitch = DEFAULT_PITCH;
        this.roll = DEFAULT_ROLL;
        this.yaw = DEFAULT_YAW;
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
        this.z = DEFAULT_Z;
        this.focalLength = DEFAULT_FOCAL_LENGTH;
    }
    /*private double[][] getCameraPosition(){
        return MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ4x4(roll), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.XZ4x4(pitch), MatrixUtils.matrixMatrixRmxnRnxp_Rmxp(MatrixUtils.YZ4x4(yaw), new double[][]{{x}, {y}, {z}, {1}})));
    }*/

    private double[] projectPoint(double[] cameraViewPoint){
        if(cameraViewPoint[2] == 0){
            return null;
        }
        double scaleFactor = focalLength/cameraViewPoint[2];
        return new double[]{cameraViewPoint[0]*scaleFactor, cameraViewPoint[1]*scaleFactor};
    
    }

}
