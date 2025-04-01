package spacevisuals.utils;

import spacevisuals.functions.Matrix;
import spacevisuals.functions.Matrix3D;
import spacevisuals.functions.Matrix4D;

public class Camera4D{

    public double x;
    public double y;
    public double z;
    public double w;
    public double XY;
    public double XZ;
    public double XW;
    public double YZ;
    public double YW;
    public double ZW;

    public Camera4D(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 0;
        this.XY = 0;
        this.XZ = 0;
        this.XW = 0;
        this.YZ = 0;
        this.YW = 0;
        this.ZW = 0;
    }

    public Camera4D(double x, double y, double z, double w, double XY, double XZ, double XW, double YZ, double YW, double ZW){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.XY = XY;
        this.XZ = XZ;
        this.XW = XW;
        this.YZ = YZ;
        this.YW = YW;
        this.ZW = ZW;
    }

    public double[] toDrawablePoint(double[] worldPoint){
        double[][] currentPosition4d = Matrix.scalarMultiply(Matrix.add(Matrix4D.ZW2x4(this.ZW), Matrix.add(Matrix4D.YW2x4(this.YW),Matrix.add(Matrix4D.YZ2x4(this.YZ), Matrix.add(Matrix4D.XW2x4(this.XW), Matrix.add(Matrix4D.XZ2x4(this.XZ), Matrix4D.XY2x4(this.XY)))))), 1.0/6);
        //double [][] projected4d = Matrix.matrixMatrixRmxnRnxp_Rmxp(projection, currentPosition4d);
        double[] translatedPoint = new double[]{worldPoint[0] - this.x, worldPoint[1] - this.y, worldPoint[2] - this.z, worldPoint[3] - this.w};
        double[] point2d = Matrix3D.matrixVectorRmxnRn_Rm(currentPosition4d, translatedPoint);
        return point2d;
    }

}