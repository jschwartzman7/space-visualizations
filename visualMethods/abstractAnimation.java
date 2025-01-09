import java.util.HashSet;

import edu.princeton.cs.introcs.StdDraw;

public abstract class abstractAnimation {

    abstractSpaceVisual space;
    int frameSpeed;

    public abstractAnimation(abstractSpaceVisual space, int frameSpeed){
        this.space = space;
        this.frameSpeed = frameSpeed;
    }
    
    abstract void update();
    abstract void draw();

    public void run(){
        while(true){
            StdDraw.clear();
            space.updateView();
            space.drawSpace();
            this.update();
            this.draw();
            StdDraw.show(frameSpeed);
        }
    }
}
