package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.SpaceColorScheme;
import spacevisuals.enums.VariableEnum;
import spacevisuals.spaces.spacemovers.SpaceMover4D;
import spacevisuals.utils.Camera4D;

public class Euclidean4D extends AbstractSpace {

    public double xAxisMin;
    public double xAxisMax;
    public double yAxisMin;
    public double yAxisMax;
    public double zAxisMin;
    public double zAxisMax;
    public double wAxisMin;
    public double wAxisMax;
    //public double[][] rotation4d;
    //public double[][] projectionR4_R2;
    public Camera4D camera;
    private static Euclidean4D instance;

    private Euclidean4D(boolean viewSpaceInfo){
        super(true);
        this.xAxisMin = -DEFAULT_CLIP_SCALE;
        this.xAxisMax = DEFAULT_CLIP_SCALE;
        this.yAxisMin = -DEFAULT_CLIP_SCALE;
        this.yAxisMax = DEFAULT_CLIP_SCALE;
        this.zAxisMin = -DEFAULT_CLIP_SCALE;
        this.zAxisMax = DEFAULT_CLIP_SCALE;
        this.wAxisMin = -DEFAULT_CLIP_SCALE;
        this.wAxisMax = DEFAULT_CLIP_SCALE;
        //this.rotation4d = new double[4][4];
        /*this.projectionR4_R2 = new double[][]{{.25, .25, .25, .25},
                                              {.25, .25, .25, .25}};*/
        //this.projectionR4_R2 = new double[][]{{1, 0, 0, 0},
        //{0, 1, 0, 0}};
        this.camera = new Camera4D();
        initializeMover();
        this.dimensions = 4;
    }

    public static Euclidean4D Get(){
        if(instance == null){
            instance = new Euclidean4D(true);
        }
        return instance;
    }
    @Override
    public void initializeColorScheme() {
        this.colorScheme = SpaceColorScheme.from("dark");
    }
    @Override
    public void initializeMover() {
        this.mover = new SpaceMover4D(this);
    }
    @Override
    public void initializeLabeler() {
    }    
    @Override
    public double[] toViewScreenPoint(double[] worldPoint) {
        return camera.toDrawablePoint(worldPoint);
        /*double wDistance = worldPoint[3] - w;
        if(!wAxis.containsKey(wDistance)){
            wAxis.put(wDistance, new Euclidean3D(false));
        }
        double[] point2d = wAxis.get(wDistance).toViewScreenPoint(rotated4dPoint);
        point2d[0] = point2d[0] + wDistance;
        point2d[1] = point2d[1] + wDistance;*/
        
    }
    @Override
    public void updateView() {
       mover.updateView();
    }
    @Override
    public void updateLabelIntervals() {
    }
    @Override
    public void drawAxis(String label){
        double[][] axis = new double[2][4];
        switch(label){
            case "x":
                axis = new double[][]{{xAxisMin, 0, 0, 0},
                                      {xAxisMax, 0, 0, 0}};
                StdDraw.setPenColor(colorScheme.xAxisColor);
                break;
            case "y":
                axis = new double[][]{{0, yAxisMin, 0, 0},
                                      {0, yAxisMax, 0, 0}};
                StdDraw.setPenColor(colorScheme.yAxisColor);
                break;
            case "z":
                axis = new double[][]{{0, 0, zAxisMin, 0},
                                      {0, 0, zAxisMax, 0}};
                StdDraw.setPenColor(colorScheme.zAxisColor);
                break;
            case "w":
                axis = new double[][]{{0, 0, 0, wAxisMin},
                                      {0, 0, 0, wAxisMax}};
                StdDraw.setPenColor(colorScheme.wAxisColor);
                break;
        }

        double[][][] partitionedAxis = partitionAxis(axis, VariableEnum.valueOf(label).precedence);
        StdDraw.setPenRadius();
        for(double[][] partition : partitionedAxis){
            if(label.equals("w")){
                double distance = Math.abs(partition[0][3] - camera.w);
                double currentWidth = StdDraw.getPenRadius();
                StdDraw.setPenRadius(currentWidth/(distance+1));
            }
            double[] axisP12D = toViewScreenPoint(new double[]{partition[0][0], partition[0][1], partition[0][2], partition[0][3]});
            double[] axisP22D = toViewScreenPoint(new double[]{partition[1][0], partition[1][1], partition[1][2], partition[1][3]});
            //StdDraw.filledCircle(axisP12D[0], axisP12D[1], .3);
            StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
        }
        //double[] axisP12D = toViewScreenPoint(new double[]{axis[0][0], axis[0][1], axis[0][2], axis[0][3]});
        double[] axisP22D = toViewScreenPoint(new double[]{axis[1][0], axis[1][1], axis[1][2], axis[1][3]});
        //StdDraw.line(axisP12D[0], axisP12D[1], axisP22D[0], axisP22D[1]);
        StdDraw.text(axisP22D[0], axisP22D[1], label);
    }
    @Override
    public void drawLabels(){
    }
}
