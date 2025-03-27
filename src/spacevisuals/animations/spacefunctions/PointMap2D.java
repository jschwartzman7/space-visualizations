package spacevisuals.animations.spacefunctions;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.SpaceFunction;


public class PointMap2D extends SpaceFunction<Euclidean2D> implements PointSetAnimation{

    private ArrayList<double[]> points;
    private ArrayList<Color> pointColors;
    private double pointRadius = 0.01;
    private ColorStrategy colorHelper;
  
    public PointMap2D(){
        super(Euclidean2D.Get(), FunctionsEnum.squared.function);
        this.points = new ArrayList<double[]>();
        this.pointColors = new ArrayList<Color>();
        this.colorHelper = new PointMapColorStrategy();
    }

    @Override
    public void updateAnimation(){
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
        PointSetAnimation.super.drawAnimation();
    }
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        StdDraw.setPenRadius(pointRadius);
        for(int i = 0; i < points.size(); i++){
            StdDraw.setPenColor(pointColors.get(i));
            handlePoint.accept(points.get(i));
        }
    }
    @Override
    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        if(output == null){
            StdDraw.point(input[0], input[1]);
            return;
        }
        if(output.length == 0){
            StdDraw.point(input[0], input[1]);
            return;
        }
        if(output.length == 1){
            StdDraw.point(input[0], output[0]);
        }
        if(output.length >= 2){
            StdDraw.point(output[0], output[1]);
        }
        StdDraw.point(input[0], input[1]);
    }
    
    @Override
    public void configureAnimation(String[] parameters) {
        setCustomFunctionStringArray(parameters);
    }
}
