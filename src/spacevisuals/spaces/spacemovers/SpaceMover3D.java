package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.SpaceUser;

public class SpaceMover3D extends SpaceUser<Euclidean3D> implements SpaceMover{

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
        super(space);
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
        getSpace().xAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        getSpace().xAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;;
        getSpace().yAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        getSpace().yAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;
        getSpace().zAxisMin = -Euclidean3D.DEFAULT_AXES_SCALE;
        getSpace().zAxisMax = Euclidean3D.DEFAULT_AXES_SCALE;
        getSpace().resetClipScale();
    }

    @Override
    public void updateView(){
        if(StdDraw.isKeyPressed(posXY)){
            getSpace().camera.pitch += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXY)){
            getSpace().camera.pitch -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posXZ)){
            getSpace().camera.roll += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXZ)){
            getSpace().camera.roll -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posYZ)){
            getSpace().camera.yaw += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negYZ)){
            getSpace().camera.yaw -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(moveRight)){
            getSpace().translateXClipPos();
        }
        else if (StdDraw.isKeyPressed(moveLeft)){
            getSpace().translateXClipNeg();
        }
        if(StdDraw.isKeyPressed(moveUp)){
            getSpace().translateYClipPos();
        }
        else if (StdDraw.isKeyPressed(moveDown)){
            getSpace().translateYClipNeg();
        }
        if(StdDraw.isKeyPressed(zoomIn)){
            getSpace().camera.focalLength *= (1+getSpace().MOVE_SENSITIVITY);
        }
        else if (StdDraw.isKeyPressed(zoomOut)){
            getSpace().camera.focalLength /= (1+getSpace().MOVE_SENSITIVITY);
        }
        if(StdDraw.isKeyPressed(increaseDomain)){
            getSpace().xAxisMin -= getSpace().getXRange()*getSpace().MOVE_SENSITIVITY;
            getSpace().xAxisMax += getSpace().getXRange()*getSpace().MOVE_SENSITIVITY;
            getSpace().yAxisMin -= getSpace().getYRange()*getSpace().MOVE_SENSITIVITY;
            getSpace().yAxisMax += getSpace().getYRange()*getSpace().MOVE_SENSITIVITY;
        }
        else if (StdDraw.isKeyPressed(decreaseDomain)){
            getSpace().xAxisMin += getSpace().getXRange()*getSpace().MOVE_SENSITIVITY;
            getSpace().xAxisMax -= getSpace().getXRange()*getSpace().MOVE_SENSITIVITY;
            getSpace().yAxisMin += getSpace().getYRange()*getSpace().MOVE_SENSITIVITY;
            getSpace().yAxisMax -= getSpace().getYRange()*getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(increaseRange)){
            getSpace().zAxisMin -= getSpace().zAxisMax*getSpace().MOVE_SENSITIVITY;
            getSpace().zAxisMax += getSpace().zAxisMax*getSpace().MOVE_SENSITIVITY;
        }
        else if (StdDraw.isKeyPressed(decreaseRange)){
            getSpace().zAxisMin += getSpace().zAxisMax*getSpace().MOVE_SENSITIVITY;
            getSpace().zAxisMax -= getSpace().zAxisMax*getSpace().MOVE_SENSITIVITY;
        }
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            resetView();
        }
    }
}
