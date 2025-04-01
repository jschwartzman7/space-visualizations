package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;


import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.SpaceUser2D;

public class SpaceMover2D implements SpaceUser2D, SpaceMover{

    int resetKey;
    int moveRight;
    int moveLeft;
    int moveUp;
    int moveDown;
    int zoomIn;
    int zoomOut;
    int xZoomIn;
    int xZoomOut;
    int yZoomIn;
    int yZoomOut;

    public SpaceMover2D(Euclidean2D space){
        this.resetKey = KeyEvent.VK_R;
        this.moveRight = KeyEvent.VK_D;
        this.moveLeft = KeyEvent.VK_A;
        this.moveUp = KeyEvent.VK_W;
        this.moveDown = KeyEvent.VK_S;
        this.zoomIn = KeyEvent.VK_Q;
        this.zoomOut = KeyEvent.VK_E;
        this.xZoomIn = KeyEvent.VK_LEFT;
        this.xZoomOut = KeyEvent.VK_RIGHT;
        this.yZoomIn = KeyEvent.VK_DOWN;
        this.yZoomOut = KeyEvent.VK_UP;
    }

    @Override
    public void resetView(){
        if (StdDraw.isKeyPressed(resetKey)){
            space().resetClipScale();
        }
    }

    @Override
    public void updateView(){
        // translate along x axis
        if(StdDraw.isKeyPressed(moveRight)){
            space().translateXClipPos();
        }
        else if (StdDraw.isKeyPressed(moveLeft)){
            space().translateXClipNeg();
        }

        // translate along y axis
        if(StdDraw.isKeyPressed(moveUp)){
            space().translateYClipPos();
        }
        else if (StdDraw.isKeyPressed(moveDown)){
            space().translateYClipNeg();
        }

        // zoom in / zoom out
        if(StdDraw.isKeyPressed(zoomIn)){
            space().zoomXClipIn();
            space().zoomYClipIn();
        }
        else if (StdDraw.isKeyPressed(zoomOut)){
            space().zoomXClipOut();
            space().zoomYClipOut();
        }
        // x axis distort
        if (StdDraw.isKeyPressed(xZoomIn)){
            space().zoomXClipIn();
        }
        else if(StdDraw.isKeyPressed(xZoomOut)){
            space().zoomXClipOut();
        }

        // y axis distort
        if (StdDraw.isKeyPressed(yZoomIn)){
            space().zoomYClipIn();
        }
        else if(StdDraw.isKeyPressed(yZoomOut)){
            space().zoomYClipOut();
        }
        resetView();
    }
    
}
