package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.SpaceUser;

public class SpaceMover4D extends SpaceUser<Euclidean4D> implements SpaceMover{

    public int resetKey;
    public int posX;
    public int negX;
    public int posY;
    public int negY;
    public int posZ;
    public int negZ;    
    public int posW;
    public int negW;
    public int posXY;
    public int negXY;
    public int posXZ;
    public int negXZ;
    public int posXW;
    public int negXW;
    public int posYZ;
    public int negYZ;
    public int posYW;
    public int negYW;
    public int posZW;
    public int negZW;
    public int zoomIn;
    public int zoomOut;

    public SpaceMover4D(Euclidean4D space){
        super(space);
        this.resetKey = KeyEvent.VK_P;
        this.posX = KeyEvent.VK_1;
        this.negX = KeyEvent.VK_2;
        this.posY = KeyEvent.VK_3;
        this.negY = KeyEvent.VK_4;
        this.posZ = KeyEvent.VK_5;
        this.negZ = KeyEvent.VK_6;
        this.posW = KeyEvent.VK_7;
        this.negW = KeyEvent.VK_8;
        this.posXY = KeyEvent.VK_Q;
        this.negXY = KeyEvent.VK_A;
        this.posXZ = KeyEvent.VK_W;
        this.negXZ = KeyEvent.VK_S;
        this.posXW = KeyEvent.VK_E;
        this.negXW = KeyEvent.VK_D;
        this.posYZ = KeyEvent.VK_R;
        this.negYZ = KeyEvent.VK_F;
        this.posYW = KeyEvent.VK_T;
        this.negYW = KeyEvent.VK_G;
        this.posZW = KeyEvent.VK_Y;
        this.negZW = KeyEvent.VK_H;
        this.zoomIn = KeyEvent.VK_DOWN;
        this.zoomOut = KeyEvent.VK_UP;
    }

    @Override
    public void resetView(){
        if (StdDraw.isKeyPressed(resetKey)){
            getSpace().resetClipScale();
        }
    }

    @Override
    public void updateView() {
        if(StdDraw.isKeyPressed(zoomOut)){
            getSpace().zoomXClipOut();
            getSpace().zoomYClipOut();
        }
        else if(StdDraw.isKeyPressed(zoomIn)){
            getSpace().zoomXClipIn();
            getSpace().zoomYClipIn();
        }
        if(StdDraw.isKeyPressed(posXY)) {
            getSpace().camera.XY += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXY)) {
            getSpace().camera.XY -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posXZ)) {
            getSpace().camera.XZ += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXZ)) {
            getSpace().camera.XZ -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posXW)) {
            getSpace().camera.XW += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXW)) {
            getSpace().camera.XW -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posYZ)) {
            getSpace().camera.YZ += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negYZ)) {
            getSpace().camera.YZ -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posYW)) {
            getSpace().camera.YW += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negYW)) {
            getSpace().camera.YW -= getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posZW)) {
            getSpace().camera.ZW += getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negZW)) {
            getSpace().camera.ZW -= getSpace().MOVE_SENSITIVITY;
        }

        if(StdDraw.isKeyPressed(posX)){
            getSpace().camera.x -= getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negX)){
            getSpace().camera.x += getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posY)){
            getSpace().camera.y -= getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negY)){
            getSpace().camera.y += getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posZ)){
            getSpace().camera.z -= getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negZ)){
            getSpace().camera.z += getSpace().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posW)){
            getSpace().camera.w -= getSpace().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negW)){
            getSpace().camera.w += getSpace().MOVE_SENSITIVITY;
        }
        resetView();
    }
    
}
