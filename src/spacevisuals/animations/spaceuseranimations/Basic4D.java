package spacevisuals.animations.spaceuseranimations;

import spacevisuals.spaces.Euclidean4D;
import spacevisuals.animations.SpaceAnimation;

public class Basic4D implements SpaceAnimation{

    public Basic4D(){
    }

    @Override
    public void drawAnimation() {
        Euclidean4D.Get().drawSpace();
    }
    
}
