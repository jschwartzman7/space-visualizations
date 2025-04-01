package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser4D;

public class SpaceMover4D implements SpaceUser4D, SpaceMover{

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
            space().resetClipScale();
        }
    }

    @Override
    public void updateView() {
        if(StdDraw.isKeyPressed(zoomOut)){
            space().zoomXClipOut();
            space().zoomYClipOut();
        }
        else if(StdDraw.isKeyPressed(zoomIn)){
            space().zoomXClipIn();
            space().zoomYClipIn();
        }
        if(StdDraw.isKeyPressed(posXY)) {
            space().camera.XY += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXY)) {
            space().camera.XY -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posXZ)) {
            space().camera.XZ += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXZ)) {
            space().camera.XZ -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posXW)) {
            space().camera.XW += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negXW)) {
            space().camera.XW -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posYZ)) {
            space().camera.YZ += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negYZ)) {
            space().camera.YZ -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posYW)) {
            space().camera.YW += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negYW)) {
            space().camera.YW -= space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posZW)) {
            space().camera.ZW += space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negZW)) {
            space().camera.ZW -= space().MOVE_SENSITIVITY;
        }

        if(StdDraw.isKeyPressed(posX)){
            space().camera.x -= space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negX)){
            space().camera.x += space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posY)){
            space().camera.y -= space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negY)){
            space().camera.y += space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posZ)){
            space().camera.z -= space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negZ)){
            space().camera.z += space().MOVE_SENSITIVITY;
        }
        if(StdDraw.isKeyPressed(posW)){
            space().camera.w -= space().MOVE_SENSITIVITY;
        }
        else if(StdDraw.isKeyPressed(negW)){
            space().camera.w += space().MOVE_SENSITIVITY;
        }
        resetView();
    }
    
}
