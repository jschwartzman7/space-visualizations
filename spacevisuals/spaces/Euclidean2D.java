package spacevisuals.spaces;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.event.KeyEvent;

public class Euclidean2D extends AbstractSpace{

    private final double X_LABEL_OFFSET = 0.02;

    public Euclidean2D(int defaultScale, double defaultLabelInterval, boolean viewSpaceInfo){
        super(defaultScale, defaultLabelInterval, viewSpaceInfo, 4, 20, 0.08, 0.1);
    }

    public void drawSpace(){
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(X_MIN, 0, X_MAX, 0);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.line(0, Y_MIN, 0, Y_MAX);
        if(VIEW_SPACE_INFO){
            StdDraw.setPenColor();
            StdDraw.setPenRadius(0.001);
            double numericMin = X_MIN*primaryDistortion;
            double numericMax = X_MAX*primaryDistortion;
            for(double numericX = numericMin-numericMin%primaryLabelInterval; numericX <= numericMax; numericX += primaryLabelInterval){
                if(Math.abs(numericX) < FLOAT_TOLERANCE){continue;}
                double x = numericX/primaryDistortion;
                StdDraw.line(x, Y_MIN, x, Y_MAX);
                StdDraw.text(x, -(Y_MAX-Y_MIN)*X_LABEL_OFFSET, toLabel(numericX));
            }
            numericMin = Y_MIN*secondaryDistortion;
            numericMax = Y_MAX*secondaryDistortion;
            for(double numericY = numericMin-numericMin%secondaryLabelInterval; numericY <= numericMax; numericY += secondaryLabelInterval){
                if(Math.abs(numericY) < FLOAT_TOLERANCE){continue;}
                double y = numericY/secondaryDistortion;
                StdDraw.line(X_MIN, y, X_MAX, y);
                StdDraw.text(0, y, toLabel(numericY));
            }
        }
    }
    public void updateView(){
        double xRange = X_MAX-X_MIN;
        double xRangeIntervalRatio = xRange / primaryLabelInterval;
        double yRange = Y_MAX-Y_MIN;
        double yRangeIntervalRatio = yRange / secondaryLabelInterval;
        updateLabelIntervals(xRangeIntervalRatio, yRangeIntervalRatio);
        
        // translate along x axis
        double xTranslationAmount = xRange*TRANSLATION_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
            translateX(xTranslationAmount);
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_A)){
            translateX(-xTranslationAmount);
        }

        // translate along y axis
        double yTranslationAmount = yRange*TRANSLATION_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)){
            translateY(yTranslationAmount);
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_S)){
            translateY(-yTranslationAmount);
        }

        // zoom in / zoom out
        double xZoomAmount = xRange*ZOOM_SENSITIVITY;
        double yZoomAmount = yRange*ZOOM_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            zoomX(-xZoomAmount);
            zoomY(-yZoomAmount);
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_E)){
            zoomX(xZoomAmount);
            zoomY(yZoomAmount);
        }
        double primaryDistortionAmount = primaryDistortion*ZOOM_SENSITIVITY;
        double secondaryDistortionAmount = secondaryDistortion*ZOOM_SENSITIVITY;
        // x axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            adjustPrimaryDistortion(-primaryDistortionAmount);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            adjustPrimaryDistortion(primaryDistortionAmount);
        }

        // y axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            adjustSecondaryDistortion(-secondaryDistortionAmount);
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            adjustSecondaryDistortion(secondaryDistortionAmount);
        }

        // reset scale
        else if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
        }

        StdDraw.setXscale(X_MIN, X_MAX);
        StdDraw.setYscale(Y_MIN, Y_MAX);
    }
}

