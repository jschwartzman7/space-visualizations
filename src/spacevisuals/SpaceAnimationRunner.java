package spacevisuals;

import spacevisuals.spaces.AbstractSpace;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

/*
 * Object for running animations in a specific space
 */
public class SpaceAnimationRunner<T extends AbstractSpace> {
    
    T space;
    int frameRate;
    LinkedList<SpaceAnimation<T>> animations;

    public SpaceAnimationRunner(T space, int frameRate){
        this.space = space;
        this.frameRate = frameRate;
        this.animations = new LinkedList<SpaceAnimation<T>>();
    }
    public SpaceAnimationRunner(T space, int frameRate, LinkedList<SpaceAnimation<T>> animations){
        this.space = space;
        this.frameRate = frameRate;
        this.animations = animations;
    }

    public void updateAnimation(){
        for(SpaceAnimation<T> animation : animations){
            animation.updateAnimation();
        }
    };
    public void drawAnimation(){
        for(SpaceAnimation<T> animation : animations){
            animation.drawAnimation();
        }
    };
    public void run(){
        while(true){
            StdDraw.clear();
            space.updateSpace();
            this.updateAnimation();
            space.drawSpace();
            this.drawAnimation();
            StdDraw.show(frameRate);
        }
    }
}
