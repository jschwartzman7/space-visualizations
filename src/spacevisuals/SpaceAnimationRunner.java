package spacevisuals;

import spacevisuals.spaces.AbstractSpace;

import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

/*
 * Object for running animations in a specific space
 */
public class SpaceAnimationRunner {
    
    AbstractSpace space;
    int frameRate;
    public LinkedList<Animation> animations;

    public SpaceAnimationRunner(AbstractSpace space, int frameRate){
        this.space = space;
        this.frameRate = frameRate;
        this.animations = new LinkedList<Animation>();
    }
    public SpaceAnimationRunner(AbstractSpace space, int frameRate, LinkedList<Animation> animations){
        this.space = space;
        this.frameRate = frameRate;
        this.animations = animations;
    }

    public void updateAnimation(){
        for(Animation animation : animations){
            animation.updateAnimation();
        }
    };
    public void drawAnimation(){
        for(Animation animation : animations){
            animation.drawAnimation();
        }
    };
    public void run(){
        while(true){
            StdDraw.clear();
            space.updateAnimation();
            this.updateAnimation();
            space.drawAnimation();
            this.drawAnimation();
            StdDraw.show(frameRate);
        }
    }
}
