package spacevisuals.animations.spaceuseranimations;

import spacevisuals.ConfigurableAnimation;
import spacevisuals.spaces.SpaceUser2D;

public class Basic2D implements ConfigurableAnimation, SpaceUser2D{

    public Basic2D(){
    }

    @Override
    public void drawAnimation() {
        space().drawSpace();
    }
    
}
