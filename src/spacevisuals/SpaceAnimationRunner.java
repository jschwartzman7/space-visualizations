package spacevisuals;

import spacevisuals.spaces.AbstractSpace;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

/*
 * Object for running animations in a specific space
 */
public class SpaceAnimationRunner {
    
    public final int CANVAS_WIDTH = 700;
    public final int CANVAS_HEIGHT = 700;
    public final int FRAMERATE;
    AbstractSpace space;
    LinkedList<SpaceAnimation> animations;

    public SpaceAnimationRunner(AbstractSpace space, int frameRate){
        this.space = space;
        this.FRAMERATE = frameRate;
        this.animations = new LinkedList<SpaceAnimation>();
        this.setCanvas();
    }
    public SpaceAnimationRunner(AbstractSpace space, int frameRate, LinkedList<SpaceAnimation> animations){
        this.space = space;
        this.FRAMERATE = frameRate;
        this.animations = animations;
        this.setCanvas();
    }

    private void setCanvas(){
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(space.xClipMin, space.xClipMax);
        StdDraw.enableDoubleBuffering();
    }

    public void updateAnimation(){
        for(SpaceAnimation animation : animations){
            animation.updateAnimation();
        }
    };
    public void drawAnimation(){
        for(SpaceAnimation animation : animations){
            animation.drawAnimation();
        }
    };
    public void run(){
        while(true){
            StdDraw.clear(space.colorScheme.backgroundColor);
            space.updateSpace();
            this.updateAnimation();
            space.drawSpace();
            this.drawAnimation();
            StdDraw.show(FRAMERATE);
        }
    }
}
