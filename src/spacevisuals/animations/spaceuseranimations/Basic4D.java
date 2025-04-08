package spacevisuals.animations.spaceuseranimations;

import spacevisuals.ConfigurableAnimation;
import spacevisuals.spaces.SpaceUser4D;

public class Basic4D implements ConfigurableAnimation, SpaceUser4D{

    public Basic4D(){
    }

    @Override
    public void drawAnimation() {
        space().drawSpace();
    }
    
}
