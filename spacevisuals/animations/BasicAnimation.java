package spacevisuals.animations;

import spacevisuals.spaces.AbstractSpace;
import edu.princeton.cs.introcs.StdDraw;


public class BasicAnimation<ConcreteSpaceVisual extends AbstractSpace> {
    
    ConcreteSpaceVisual space;
    int frameRate;

    public BasicAnimation(ConcreteSpaceVisual space, int frameRate){
        this.space = space;
        this.frameRate = frameRate;
    }
    public void updateAnimation(){};
    public void drawAnimation(){};
    public void run(){
        while(true){
            StdDraw.clear();
            space.updateView();
            this.updateAnimation();
            space.drawSpace();
            this.drawAnimation();
            StdDraw.show(frameRate);
        }
    }
}
