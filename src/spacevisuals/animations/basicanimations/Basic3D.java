package spacevisuals.animations.basicanimations;

import spacevisuals.ConfigurableAnimation;
import spacevisuals.spaces.SpaceUser3D;

public class Basic3D implements ConfigurableAnimation, SpaceUser3D{

    public Basic3D(){
    }

    @Override
    public void drawAnimation() {
        space().drawSpace();
    }
    
}
