package spacevisuals.animations.pointsetanimations;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.function.Consumer;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.SpaceFunction2D;
import spacevisuals.PointSetAnimation;
import spacevisuals.animations.vectorfieldanimations.VectorField2D;
import spacevisuals.functions.functionhandling.FunctionsEnum;

public class Gradient extends SpaceFunction2D implements PointSetAnimation{
    
    private LinkedList<double[]> points;
    //private LinkedList<Color> pointColors;
    private double distanceStep = 0.01;
    private double pointRadius = 0.02;
    private VectorField2D vectorField;

    public Gradient(){
        super();
        this.points = new LinkedList<double[]>();
        this.vectorField = new VectorField2D();
    }
    @Override
    public void updateAnimation(){
        vectorField.updateAnimation();
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            points.add(newPoint);
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
            points.clear();
        }
    }
    @Override
    public void drawAnimation(){
        vectorField.drawAnimation();
        traverseDomain(this::handlePoint);
    };
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint) {
        for(double[] point : points){
            handlePoint.accept(point);
        }
    }
    @Override
    public void handlePoint(double[] point) {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledCircle(point[0], point[1], pointRadius);
        double[] output = function.apply(point);
        double[] vector = new double[]{output[0]-point[0], output[1]-point[1]};
        double vectorMagnitude = Math.sqrt(vector[0]*vector[0]+vector[1]*vector[1]);
        point[0] += distanceStep*vector[0]/vectorMagnitude;
        point[1] += distanceStep*vector[1]/vectorMagnitude;
    }
    @Override
    public void buildAnimation(String[] parameters) {
        this.function = FunctionsEnum.from(parameters[0]).getFunction();
    }
}
