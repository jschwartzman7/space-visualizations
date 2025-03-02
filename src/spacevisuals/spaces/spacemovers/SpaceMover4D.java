package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser;

public class SpaceMover4D extends SpaceUser<Euclidean4D> implements SpaceMover{

    public double x = 0;
    public double y = 0;
    public double z = 0;
    public double w = 0;
    public double xy = 0;
    public double xz = 0;
    public double xw = 0;
    public double yz = 0;
    public double yw = 0;
    public double zw = 0;

    public SpaceMover4D(Euclidean4D space){
        super(space);
    }

    public void resetView(){
        if (StdDraw.isKeyPressed(KeyEvent.VK_SHIFT) && StdDraw.isKeyPressed(KeyEvent.VK_R)){
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.w = 0;
            this.xy = 0;
            this.xz = 0;
            this.xw = 0;
            this.yz = 0;
            this.yw = 0;
            this.zw = 0;
        }
    }

    @Override
    public void updateView() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
            space.zoomXClipOut();
            space.zoomYClipOut();
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
            space.zoomXClipIn();
            space.zoomYClipIn();
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
            this.xy += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            this.xy -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            this.xz += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            this.xz -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
            this.xw += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            this.xw -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_R)) {
            this.yz += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_F)) {
            this.yz -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_T)) {
            this.yw += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_G)) {
            this.yw -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
            this.zw += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_H)) {
            this.zw -= space.ROTATION_RATE;
        }

        if(StdDraw.isKeyPressed(KeyEvent.VK_1)){
            this.x -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_2)){
            this.x += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_3)){
            this.y -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_4)){
            this.y += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_5)){
            this.z -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_6)){
            this.z += space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_7)){
            this.w -= space.ROTATION_RATE;
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_8)){
            this.w += space.ROTATION_RATE;
        }
        resetView();
    }
    
}
