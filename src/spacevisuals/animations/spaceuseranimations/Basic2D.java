package spacevisuals.animations.spaceuseranimations;

import spacevisuals.SpaceAnimation;
import spacevisuals.spaces.Euclidean2D;

public class Basic2D implements SpaceAnimation{

    public Basic2D(){
    }

    @Override
    public void drawAnimation() {
        Euclidean2D.Get().drawSpace();
    }
    
}
