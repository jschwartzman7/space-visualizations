package spacevisuals.animations.spaceuseranimations;

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
