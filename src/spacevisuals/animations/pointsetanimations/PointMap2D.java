package spacevisuals.animations.pointsetanimations;

import spacevisuals.spaces.*;
import spacevisuals.SpaceFunction2D;
import edu.princeton.cs.introcs.StdDraw;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.*;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.functions.functionhandling.FunctionsEnum;


public class PointMap2D extends SpaceFunction2D implements PointSetAnimation{

    private LinkedList<double[]> points;
    private LinkedList<Color> pointColors;
    private double pointRadius = 0.01;
  
    public PointMap2D(){
        super();
        this.points = new LinkedList<double[]>();
        this.pointColors = new LinkedList<Color>();
    }

    @Override
    public void updateAnimation(){
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            points.add(newPoint);
            pointColors.add(ColorStrategy.getRandomColor());
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
        StdDraw.point(input[0], input[1]);
        StdDraw.point(output[0], output[1]);
    }
    @Override
    public void buildAnimation(String[] parameters) {
        setFunctionStringArray(parameters);
    }
}
