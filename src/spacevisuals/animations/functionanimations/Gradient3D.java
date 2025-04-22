package spacevisuals.animations.functionanimations;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.animations.PointSetAnimation;
import spacevisuals.animations.functionanimations.spacetraverseranimations.functiongraphs.Graph3D;
import spacevisuals.animations.functionanimations.spacetraverseranimations.functiongraphs.Graph3DTriangle;
import spacevisuals.spaces.SpaceUser3D;
import spacevisuals.utils.Constants;
import spacevisuals.utils.PointFinderPlane3D;

public class Gradient3D extends PointSetAnimation implements SpaceUser3D{

    public Graph3D graph3d;
    public PointFinderPlane3D pointFinder;

    @Override
    public void updateAnimation(){
        graph3d.updateAnimation();
        if(StdDraw.isMousePressed()){
            double [] newPoint = new double[]{StdDraw.mouseX(), StdDraw.mouseY()};
            this.points.add(findPoint(newPoint));
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_R)){
            this.points.clear();
        }
    }

    private double[] findPoint(double[] newPoint) {
        return pointFinder.findPoint(newPoint);
    }

    @Override
    public void drawAnimation(){
        graph3d.drawAnimation();
    }

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
        graph3d.configureAnimation(parameters);
        super.configureAnimation(parameters);
    }
}
