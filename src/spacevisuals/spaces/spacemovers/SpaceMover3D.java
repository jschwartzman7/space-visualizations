package spacevisuals.spaces.spacemovers;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class SpaceMover3D extends SpaceUser<Euclidean3D> implements SpaceMover{

    public SpaceMover3D(Euclidean3D space){
        super(space);
    }

    public void resetView(){
        if (StdDraw.isKeyPressed(KeyEvent.VK_R)){
            space.resetClipScale();
            space.camera.resetCamera();
            space.xAxisMin = -space.DEFAULT_CLIP_SCALE;
            space.xAxisMax = space.DEFAULT_CLIP_SCALE;
            space.yAxisMin = -space.DEFAULT_CLIP_SCALE;
            space.yAxisMax = space.DEFAULT_CLIP_SCALE;
            space.zAxisMin = -space.DEFAULT_CLIP_SCALE;
            space.zAxisMax = space.DEFAULT_CLIP_SCALE;
        }
    }

    public void updateView(){
        double primaryRange = space.xAxisMin-space.xAxisMax;
        double primaryZoomAmount = primaryRange*space.MOVE_SENSITIVITY;
        double secondaryRange = space.zAxisMin-space.zAxisMax;
        double secondaryZoomAmount = secondaryRange*space.MOVE_SENSITIVITY;
        if(StdDraw.isKeyPressed(KeyEvent.VK_SHIFT)){
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                space.camera.x += primaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                space.camera.x -= primaryZoomAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                space.camera.y += primaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                space.camera.y -= primaryZoomAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                space.camera.z += secondaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                space.camera.z -= secondaryZoomAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                space.camera.focalLength *= (1.05);
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                space.zoomXClipOut();
                space.zoomYClipOut();
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                space.zoomXClipIn();
                space.zoomYClipIn();
            }
        }
        else{
            if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                space.camera.pitch += space.ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                space.camera.pitch -= space.ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_E)) {
                space.camera.roll += space.ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
                space.camera.roll -= space.ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
                space.camera.yaw += space.ROTATION_RATE;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                space.camera.yaw -= space.ROTATION_RATE;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_F)){
                space.camera.focalLength *= (0.95);
            }
            
            
            if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
                space.zAxisMin -= secondaryZoomAmount;
                space.zAxisMax += secondaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
                space.zAxisMin += secondaryZoomAmount;
                space.zAxisMax -= secondaryZoomAmount;
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                space.xAxisMin += primaryZoomAmount;
                space.xAxisMax -= primaryZoomAmount;
                space.yAxisMin += primaryZoomAmount;
                space.yAxisMax -= primaryZoomAmount;
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                space.xAxisMin -= primaryZoomAmount;
                space.xAxisMax += primaryZoomAmount;
                space.yAxisMin -= primaryZoomAmount;
                space.yAxisMax += primaryZoomAmount;
            }
            resetView();
        }
    }
    
}
