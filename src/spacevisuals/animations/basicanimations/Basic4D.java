package spacevisuals.animations.basicanimations;

import spacevisuals.spaces.SpaceUser4D;
import spacevisuals.utils.ConfigurableAnimation;

public class Basic4D implements ConfigurableAnimation, SpaceUser4D{

    public Basic4D(){
    }

    @Override
    public void drawAnimation() {
        space().drawSpace();
    }
    
}
