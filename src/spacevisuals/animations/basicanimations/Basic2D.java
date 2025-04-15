package spacevisuals.animations.basicanimations;

import spacevisuals.spaces.SpaceUser2D;
import spacevisuals.utils.ConfigurableAnimation;

public class Basic2D implements ConfigurableAnimation, SpaceUser2D{

    public Basic2D(){
    }

    @Override
    public void drawAnimation() {
        space().drawSpace();
    }
    
}
