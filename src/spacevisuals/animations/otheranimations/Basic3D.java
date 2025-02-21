package spacevisuals.animations.otheranimations;

import spacevisuals.SpaceFunction3D;
import spacevisuals.SpaceAnimation;

public class Basic3D extends SpaceFunction3D implements SpaceAnimation{

    public Basic3D(){
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
