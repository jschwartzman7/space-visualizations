package spacevisuals.animations.functionanimations;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.functionanimations.spacetraverseranimations.vectorfields.VectorField2D;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.utils.Constants;

public class Gradient extends PointSetAnimation implements SpaceUser2D{
    
    private VectorField2D vectorField;
    private PointMapColorStrategy colorHelper;

    public Gradient(){
        super();
        this.vectorField = new VectorField2D();
        this.colorHelper = new PointMapColorStrategy();
        this.defaultFunction = (double[] input) -> new double[]{Math.sin(input[0]), Math.cos(input[1])};
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
        colorHelper.resetHue();
        traverseDomain(this::handlePoint);
    };
    @Override
    public void handleInputOutput(double[] input, double[] output) {
        StdDraw.setPenColor(colorHelper.getColor(input));
        StdDraw.filledCircle(input[0], input[1], Constants.POINT_WIDTH);
        output[0] *= Constants.DISTANCE_STEP;
        output[1] *= Constants.DISTANCE_STEP;
        input[0] += output[0];
        input[1] += output[1];
    }
    @Override
    public void configureAnimation(String[] parameters) {
        vectorField.configureAnimation(parameters);
        super.configureAnimation(parameters);
    }
}
