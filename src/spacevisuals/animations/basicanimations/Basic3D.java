package spacevisuals.animations.basicanimations;

import spacevisuals.spaces.SpaceUser3D;
import spacevisuals.utils.ConfigurableAnimation;

public class Basic3D implements ConfigurableAnimation, SpaceUser3D{

    public Basic3D(){
    }

    @Override
    public void drawAnimation() {
        space().drawSpace();
    }
    
}
