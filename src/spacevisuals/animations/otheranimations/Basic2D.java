package spacevisuals.animations.otheranimations;

import spacevisuals.SpaceFunction2D;
import spacevisuals.SpaceAnimation;

public class Basic2D extends SpaceFunction2D implements SpaceAnimation{

    public Basic2D(){
        super();
    }

    @Override
    public void drawAnimation() {
        space.drawSpace();
    }

    @Override
    public void buildAnimation(String[] parameters) {
    }
    
}
