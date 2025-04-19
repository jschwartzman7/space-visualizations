package spacevisuals.animations.functionanimations.pointsetanimations;

import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.colors.colorstrategies.ColorStrategy;
import spacevisuals.colors.colorstrategies.PointMapColorStrategy;
import spacevisuals.enums.FunctionsEnum;
import spacevisuals.utils.Constants;


public class PointMap2D extends PointSetAnimation{

    public PointMap2D(){
        super(FunctionsEnum.squared.function);
        this.colorHelper = new PointMapColorStrategy();
    }

    @Override
    public void updateAnimation(){
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            this.points.add(newPoint);
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
            this.points.clear();
        }
        ((PointMapColorStrategy) colorHelper).resetHue();
    }
    @Override
    public void handleInputOutput(double[] input, double[] output){
        StdDraw.setPenColor(colorHelper.getColor(input));
        if(output == null || output.length == 0){
            StdDraw.filledCircle(input[0], input[1], Constants.POINT_WIDTH);
            return;
        }
        if(output.length == 1){
            StdDraw.filledCircle(input[0], output[0], Constants.POINT_WIDTH);
            return;
        }
        StdDraw.filledCircle(output[0], output[1], Constants.POINT_WIDTH); 
    }
}
