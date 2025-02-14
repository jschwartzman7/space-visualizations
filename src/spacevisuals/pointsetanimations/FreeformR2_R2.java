package spacevisuals.pointsetanimations;

import spacevisuals.functions.C_C;
import spacevisuals.spaces.*;
import edu.princeton.cs.introcs.StdDraw;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.awt.Color;
import java.awt.event.KeyEvent;

import spacevisuals.*;
import spacevisuals.helpers.*;


public class FreeformR2_R2 extends PointSetAnimation<Euclidean2D>{

    private LinkedList<double[]> points;
    private LinkedList<Color> pointColors;
    private double pointRadius = 0.01;
  
    public FreeformR2_R2(Euclidean2D space, Function<double[], double[]> function){
        super(space, function);
        this.space = space;
        this.function = function;
        this.points = new LinkedList<double[]>();
        this.pointColors = new LinkedList<Color>();
    }

    public void drawAnimation(){
        traverseDomain(this::handlePoint);
    }

    public void traverseDomain(Consumer<double[]> handlePoint){
        StdDraw.setPenRadius(pointRadius);
        for(int i = 0; i < points.size(); i++){
            StdDraw.setPenColor(pointColors.get(i));
            handlePoint.accept(points.get(i));
        }
    }

    public void updateAnimation(){
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            points.add(newPoint);
            pointColors.add(ColorHelper.getRandomColor());
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
            points.clear();
        }
    }

    public void handlePoint(double[] input){
        double[] output = function.apply(input);
        StdDraw.point(input[0], input[1]);
        StdDraw.point(output[0], output[1]);
    }
}
