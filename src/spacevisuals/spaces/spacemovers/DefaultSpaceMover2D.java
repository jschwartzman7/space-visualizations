package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean2D;

public class DefaultSpaceMover2D implements SpaceMover{

    public Euclidean2D space;

    public DefaultSpaceMover2D(Euclidean2D space){
        this.space = space;
    }

    public void resetView(){
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            space.resetClipScale();
        }
    }

    public void updateView(){
        // translate along x axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
            space.translateXClipPos();
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_A)){
            space.translateXClipNeg();
        }

        // translate along y axis
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)){
            space.translateYClipPos();
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_S)){
            space.translateYClipNeg();
        }

        // zoom in / zoom out
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
            space.zoomXClipIn();
            space.zoomYClipIn();
        }
        else if (StdDraw.isKeyPressed(KeyEvent.VK_E)){
            space.zoomXClipOut();
            space.zoomYClipOut();
        }
        // x axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            space.zoomXClipIn();
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            space.zoomXClipOut();
        }

        // y axis distort
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            space.zoomYClipIn();
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            space.zoomYClipOut();
        }
        resetView();
    }
    
}
