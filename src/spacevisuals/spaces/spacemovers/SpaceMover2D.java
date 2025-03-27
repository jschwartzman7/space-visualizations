package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;


import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.SpaceUser;

public class SpaceMover2D extends SpaceUser<Euclidean2D> implements SpaceMover{

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
        super(space);
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
            getSpace().resetClipScale();
        }
    }

    @Override
    public void updateView(){
        // translate along x axis
        if(StdDraw.isKeyPressed(moveRight)){
            getSpace().translateXClipPos();
        }
        else if (StdDraw.isKeyPressed(moveLeft)){
            getSpace().translateXClipNeg();
        }

        // translate along y axis
        if(StdDraw.isKeyPressed(moveUp)){
            getSpace().translateYClipPos();
        }
        else if (StdDraw.isKeyPressed(moveDown)){
            getSpace().translateYClipNeg();
        }

        // zoom in / zoom out
        if(StdDraw.isKeyPressed(zoomIn)){
            getSpace().zoomXClipIn();
            getSpace().zoomYClipIn();
        }
        else if (StdDraw.isKeyPressed(zoomOut)){
            getSpace().zoomXClipOut();
            getSpace().zoomYClipOut();
        }
        // x axis distort
        if (StdDraw.isKeyPressed(xZoomIn)){
            getSpace().zoomXClipIn();
        }
        else if(StdDraw.isKeyPressed(xZoomOut)){
            getSpace().zoomXClipOut();
        }

        // y axis distort
        if (StdDraw.isKeyPressed(yZoomIn)){
            getSpace().zoomYClipIn();
        }
        else if(StdDraw.isKeyPressed(yZoomOut)){
            getSpace().zoomYClipOut();
        }
        resetView();
    }
    
}
