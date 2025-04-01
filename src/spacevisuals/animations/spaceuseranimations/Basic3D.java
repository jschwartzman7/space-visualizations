package spacevisuals.animations.spaceuseranimations;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.animations.SpaceAnimation;

public class Basic3D implements SpaceAnimation{

    public Basic3D(){
    }

    @Override
    public void drawAnimation() {
        Euclidean3D.Get().drawSpace();
    }
    
}
