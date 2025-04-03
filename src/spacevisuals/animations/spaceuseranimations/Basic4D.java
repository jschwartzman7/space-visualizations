package spacevisuals.animations.spaceuseranimations;

import spacevisuals.SpaceAnimation;
import spacevisuals.spaces.Euclidean4D;

public class Basic4D implements SpaceAnimation{

    public Basic4D(){
    }

    @Override
    public void drawAnimation() {
        Euclidean4D.Get().drawSpace();
    }
    
}
