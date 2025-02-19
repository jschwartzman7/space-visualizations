package spacevisuals.spaces;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.function.Consumer;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.functions.Matrix3D;
import spacevisuals.functions.Matrix4D;
import spacevisuals.spaces.Euclidean3D;

public class Euclidean4DTranslation extends AbstractSpace {

    // w -> 3D(x,y,x)
    public HashMap<Double, Euclidean3D> wAxis;
    public double w;
    public double wViewRadius;
    public double wResolution;
    public double[][] rotation4d;
    Euclidean3D space3D = Euclidean3D.Euclidean3DGet();

    public Euclidean4DTranslation(boolean viewSpaceInfo){
        super(viewSpaceInfo);
        this.wAxis = new HashMap<Double, Euclidean3D>();
        this.w = 0;
        this.wViewRadius = 1;
        this.wResolution = 0.1;
        this.rotation4d = new double[][]{{1, 1, 1, 1},
                                         {1, 1, 1, 1}};
    }

    @Override
    public double[] toViewScreenPoint(double[] worldPoint) {
        /*double wDistance = worldPoint[3] - w;
        if(!wAxis.containsKey(wDistance)){
            wAxis.put(wDistance, new Euclidean3D(false));
        }
        double[] point2d = wAxis.get(wDistance).toViewScreenPoint(rotated4dPoint);
        point2d[0] = point2d[0] + wDistance;
        point2d[1] = point2d[1] + wDistance;*/
        double[] point2d = Matrix3D.matrixVectorRmxnRn_Rm(rotation4d, worldPoint);
        return point2d;
    }
    @Override
    public void updateSpace(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            rotation4d = Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix4D.XY2x4(space3D.ROTATION_RATE), rotation4d);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            rotation4d = Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix4D.XY2x4(-space3D.ROTATION_RATE), rotation4d);
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
            rotation4d = Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix4D.XZ2x4(space3D.ROTATION_RATE), rotation4d);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
            rotation4d = Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix4D.XZ2x4(-space3D.ROTATION_RATE), rotation4d);
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            rotation4d = Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix4D.YZ2x4(space3D.ROTATION_RATE), rotation4d);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            rotation4d = Matrix3D.matrixMatrixRmxnRnxp_Rmxp(Matrix4D.YZ2x4(-space3D.ROTATION_RATE), rotation4d);
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
            space3D.camera.focalLength *= (1.05);
        }
    };
    private void drawAxis(double[][] axis){
        double[] axisP12D = toViewScreenPoint(new double[]{axis[0][0], axis[0][1], axis[0][2], axis[0][3]});
        double[] axisP22D = toViewScreenPoint(new double[]{axis[1][0], axis[1][1], axis[1][2], axis[1][3]});
        StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
    }
    @Override
    public void drawAxes() {
        drawAxis(new double[][]{{space3D.xAxisMin, 0, 0, 0}, {space3D.xAxisMax, 0, 0, 0}});
        drawAxis(new double[][]{{0, space3D.yAxisMin, 0, 0}, {0, space3D.yAxisMax, 0, 0}});
        drawAxis(new double[][]{{0, 0, space3D.zAxisMin, 0}, {0, 0, space3D.zAxisMax, 0}});
        drawAxis(new double[][]{{0, 0, 0, w-wViewRadius}, {0, 0, 0, w+wViewRadius}});
    }
    @Override
    public void drawLabels(){
    }

    @Override
    public void initializeLabeler() {}

    public void traverseWAxis(Consumer<Euclidean3D> function){
        for(double wCur = w-wViewRadius; wCur < w+wViewRadius; wCur += wResolution){
            if(!wAxis.containsKey(wCur)){
                wAxis.put(wCur, Euclidean3D.Euclidean3DGet());
            }
            Euclidean3D euclidean3D = wAxis.get(wCur);
            function.accept(euclidean3D);
        }
    }

    @Override
    public void initializeMover() {
        // TODO Auto-generated method stub
    }

    @Override
    public void initializeColorScheme() {
        // TODO Auto-generated method stub
    }
    
}
