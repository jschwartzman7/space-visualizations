package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.helpers.*;
import spacevisuals.SpaceAnimation;
import java.awt.event.KeyEvent;

public class Euclidean2D extends AbstractSpace{

    private final double X_LABEL_OFFSET = 0.02;
    

    public Euclidean2D(boolean viewSpaceInfo){
        super(viewSpaceInfo);
        if(viewSpaceInfo){
            initializeLabeler();
        }
    }
    public Euclidean2D(int defaultScale, double moveSensitivity, boolean viewSpaceInfo){
        super(defaultScale, moveSensitivity, viewSpaceInfo);
        if(viewSpaceInfo){
            initializeLabeler();
        }
    }

    private void initializeLabeler(){
        this.labeler = new AxisLabeler(new double[]{DEFAULT_CLIP_SCALE, DEFAULT_CLIP_SCALE}, new double[][]{{3, 8}, {3, 8}});
    }
    public double[] toViewScreenPoint(double[] numericPoint){
        return new double[]{numericPoint[0], numericPoint[1]};
    }

    public void drawAxes(){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        double yCord;
        if(yClipMax < 0){
            yCord = yClipMax;
        }
        else if (yClipMin > 0){
            yCord = yClipMin;
        }
        else{
            yCord = 0;
        }
        StdDraw.line(xClipMin, yCord, xClipMax, yCord);
        StdDraw.setPenColor(StdDraw.GREEN);
        double xCord;
        if(xClipMax < 0){
            xCord = xClipMax;
        }
        else if (xClipMin > 0){
            xCord = xClipMin;
        }
        else{
            xCord = 0;
        }
        StdDraw.line(xCord, yClipMin, xCord, yClipMax);
    }

    public void drawLabels(){
        StdDraw.setPenColor();
        StdDraw.setPenRadius(0.001);
        double numericMin = xClipMin;
        double numericMax = xClipMax;
        for(double numericX = numericMin-numericMin%labeler.labelIntervals[0]; numericX <= numericMax; numericX += labeler.labelIntervals[0]){
            if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
            double x = numericX;
            StdDraw.line(x, yClipMin, x, yClipMax);
            StdDraw.text(x, -(yClipMax-yClipMin)*X_LABEL_OFFSET, toLabel(numericX));
        }
        numericMin = yClipMin;
        numericMax = yClipMax;
        for(double numericY = numericMin-numericMin%labeler.labelIntervals[1]; numericY <= numericMax; numericY += labeler.labelIntervals[1]){
            if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
            double y = numericY;
            StdDraw.line(xClipMin, y, xClipMax, y);
            StdDraw.text(0, y, toLabel(numericY));
        }
    }

    public void updateLabels(){
        if(VIEW_SPACE_INFO){
            double xClipRange = xClipMax-xClipMin;
            labeler.updateLabelInterval(0, xClipRange);
            double yClipRange = yClipMax-yClipMin;
            labeler.updateLabelInterval(1, yClipRange);
        }
    }
    
    public void updateView(){
        // translate along x axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
            translateXClipPos();
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_A)){
            translateXClipNeg();
        }

        // translate along y axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)){
            translateYClipPos();
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_S)){
            translateYClipNeg();
        }

        // zoom in / zoom out
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            zoomXClipIn();
            zoomYClipIn();
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_E)){
            zoomXClipOut();
            zoomYClipOut();
        }
        // x axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            zoomXClipIn();
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            zoomXClipOut();
        }

        // y axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            zoomYClipIn();
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            zoomYClipOut();
        }

        // reset scale
        else if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetClipScale();
        }
        setSpaceScale();
    }
}

