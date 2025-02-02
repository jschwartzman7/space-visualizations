package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.KeyEvent;

public class Euclidean2D extends AbstractSpace{

    private final double X_LABEL_OFFSET = 0.02;

    public Euclidean2D(int defaultScale, double defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 5, 14, 0.08);
    }

    public double[] toDrawablePoint(double[] numericPoint){
        return new double[]{numericPoint[0], numericPoint[1]};
    }

    public void drawAxes(){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        double yCord;
        if(yMaxClip < 0){
            yCord = yMaxClip;
        }
        else if (yMinClip > 0){
            yCord = yMinClip;
        }
        else{
            yCord = 0;
        }
        StdDraw.line(xMinClip, yCord, xMaxClip, yCord);
        StdDraw.setPenColor(StdDraw.GREEN);
        double xCord;
        if(xMaxClip < 0){
            xCord = xMaxClip;
        }
        else if (xMinClip > 0){
            xCord = xMinClip;
        }
        else{
            xCord = 0;
        }
        StdDraw.line(xCord, yMinClip, xCord, yMaxClip);
    }

    public void drawSpaceInfo(){
        StdDraw.setPenColor();
        StdDraw.setPenRadius(0.001);
        double numericMin = xMinClip;
        double numericMax = xMaxClip;
        for(double numericX = numericMin-numericMin%primaryLabelInterval; numericX <= numericMax; numericX += primaryLabelInterval){
            if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
            double x = numericX;
            StdDraw.line(x, yMinClip, x, yMaxClip);
            StdDraw.text(x, -(yMaxClip-yMinClip)*X_LABEL_OFFSET, toLabel(numericX));
        }
        numericMin = yMinClip;
        numericMax = yMaxClip;
        for(double numericY = numericMin-numericMin%secondaryLabelInterval; numericY <= numericMax; numericY += secondaryLabelInterval){
            if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
            double y = numericY;
            StdDraw.line(xMinClip, y, xMaxClip, y);
            StdDraw.text(0, y, toLabel(numericY));
        }
    }

    public void updateLabels(){
        updateLabelIntervals(xMaxClip-xMinClip, yMaxClip-yMinClip);
    }
    
    public void updateView(){
        double xRange = xMaxClip-xMinClip;
        double yRange = yMaxClip-yMinClip;
        // translate along x axis
        double xTranslationAmount = xRange*MOVE_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
            translateXClip(xTranslationAmount);
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_A)){
            translateXClip(-xTranslationAmount);
        }

        // translate along y axis
        double yTranslationAmount = yRange*MOVE_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)){
            translateYClip(yTranslationAmount);
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_S)){
            translateYClip(-yTranslationAmount);
        }

        // zoom in / zoom out
        double xZoomAmount = xRange*MOVE_SENSITIVITY;
        double yZoomAmount = yRange*MOVE_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            zoomXClip(-xZoomAmount);
            zoomYClip(-yZoomAmount);
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_E)){
            zoomXClip(xZoomAmount);
            zoomYClip(yZoomAmount);
        }
        // x axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            zoomXClip(-xZoomAmount);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            zoomXClip(xZoomAmount);
        }

        // y axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            zoomYClip(-yZoomAmount);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            zoomYClip(yZoomAmount);
        }

        // reset scale
        else if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
        }
        setSpaceScale();
    }
}

