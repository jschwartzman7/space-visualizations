package spacevisuals.animations.spacefunctions.vectorfields;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.FunctionAnimation;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.spaces.Euclidean2D;

public class Gradient extends PointSetAnimation{
    
    private LinkedList<double[]> points;
    private ArrayList<Color> pointColors;
    private double distanceStep = 0.1;
    private double pointScale = 0.005;
    private VectorField2D vectorField;
    private PointMapColorStrategy colorHelper;

    public Gradient(){
        super(FunctionsEnum.sin.function);
        this.points = new LinkedList<double[]>();
        this.pointColors = new ArrayList<Color>();
        this.vectorField = new VectorField2D();
        this.colorHelper = new PointMapColorStrategy();
    }
    @Override
    public void updateAnimation(){
        vectorField.updateAnimation();
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            points.add(newPoint);
            pointColors.add(colorHelper.getColor(newPoint));
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
            points.clear();
        }
    }
    @Override
    public void drawAnimation(){
        vectorField.drawAnimation();
        traverseDomain();
    };
    @Override
    public void traverseDomain(){
        StdDraw.setPenRadius(pointScale);
        for(int i = 0; i < points.size(); i++){
            StdDraw.setPenColor(pointColors.get(i));
            handlePoint(points.get(i));
        }
    }
    @Override
    public void handlePoint(double[] point) {
        if(Double.isNaN(point[0]) || Double.isNaN(point[1]) || Double.isInfinite(point[0]) || Double.isInfinite(point[1])){
            return;
        }
        StdDraw.filledCircle(point[0], point[1], Math.min(Euclidean2D.Get().xClipMax-Euclidean2D.Get().xClipMin, Euclidean2D.Get().yClipMax-Euclidean2D.Get().yClipMin)*pointScale);
        double[] output = function.apply(point);
        output[0] *= distanceStep;
        output[1] *= distanceStep;
        point[0] += output[0];
        point[1] += output[1];
    }
    @Override
    public void configureAnimation(String[] parameters) {
        vectorField.configureAnimation(parameters);
        super.configureAnimation(parameters);
    }
}
