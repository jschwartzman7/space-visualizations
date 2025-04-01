package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser3D;

public class SpaceMover3D implements SpaceUser3D, SpaceMover{

    int resetKey;
    int posXY;
    int negXY;
    int posXZ;
    int negXZ;
    int posYZ;
    int negYZ;
    int moveRight;
    int moveLeft;
    int moveUp;
    int moveDown;
    int zoomIn;
    int zoomOut;
    int increaseDomain;
    int decreaseDomain;
    int increaseRange;
    int decreaseRange;

    public SpaceMover3D(Euclidean3D space){
        this.resetKey = KeyEvent.VK_R;
        this.posXY = KeyEvent.VK_D;
        this.negXY = KeyEvent.VK_A;
        this.posXZ = KeyEvent.VK_E;
        this.negXZ = KeyEvent.VK_Q;
        this.posYZ = KeyEvent.VK_W;
        this.negYZ = KeyEvent.VK_S;
        this.moveRight = KeyEvent.VK_RIGHT;
        this.moveLeft = KeyEvent.VK_LEFT;
        this.moveUp = KeyEvent.VK_UP;
        this.moveDown = KeyEvent.VK_DOWN;
        this.zoomIn = KeyEvent.VK_F;
        this.zoomOut = KeyEvent.VK_T;
        this.increaseDomain = KeyEvent.VK_X;
        this.decreaseDomain = KeyEvent.VK_Y;
        this.increaseRange = KeyEvent.VK_Z;
        this.decreaseRange = KeyEvent.VK_C;
    }
    
    @Override
    public void resetView(){
        space().xAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        space().xAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;;
        space().yAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        space().yAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;
        space().zAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        space().zAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;
        space().resetClipScale();
    }

    @Override
    public void updateView(){
        if(StdDraw.isKeyPressed(posXY)){
            space().camera.pitch += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXY)){
            space().camera.pitch -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posXZ)){
            space().camera.roll += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXZ)){
            space().camera.roll -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posYZ)){
            space().camera.yaw += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negYZ)){
            space().camera.yaw -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(moveRight)){
            space().translateXClipPos();
        }
        else if (StdDraw.isKeyPressed(moveLeft)){
            space().translateXClipNeg();
        }
        if(StdDraw.isKeyPressed(moveUp)){
            space().translateYClipPos();
        }
        else if (StdDraw.isKeyPressed(moveDown)){
            space().translateYClipNeg();
        }
        if(StdDraw.isKeyPressed(zoomIn)){
            space().camera.focalLength *= (1+space().MOVE_SENSITIVITY);
        }
        else if (StdDraw.isKeyPressed(zoomOut)){
            space().camera.focalLength /= (1+space().MOVE_SENSITIVITY);
        }
        if(StdDraw.isKeyPressed(increaseDomain)){
            space().xAxisMin -= space().getXRange()*space().MOVE_SENSITIVITY;
            space().xAxisMax += space().getXRange()*space().MOVE_SENSITIVITY;
            space().yAxisMin -= space().getYRange()*space().MOVE_SENSITIVITY;
            space().yAxisMax += space().getYRange()*space().MOVE_SENSITIVITY;
        }
        else if (StdDraw.isKeyPressed(decreaseDomain)){
            space().xAxisMin += space().getXRange()*space().MOVE_SENSITIVITY;
            space().xAxisMax -= space().getXRange()*space().MOVE_SENSITIVITY;
            space().yAxisMin += space().getYRange()*space().MOVE_SENSITIVITY;
            space().yAxisMax -= space().getYRange()*space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(increaseRange)){
            space().zAxisMin -= space().zAxisMax*space().MOVE_SENSITIVITY;
            space().zAxisMax += space().zAxisMax*space().MOVE_SENSITIVITY;
        }
        else if (StdDraw.isKeyPressed(decreaseRange)){
            space().zAxisMin += space().zAxisMax*space().MOVE_SENSITIVITY;
            space().zAxisMax -= space().zAxisMax*space().MOVE_SENSITIVITY;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
        }
    }
}
