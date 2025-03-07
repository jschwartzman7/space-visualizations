package spacevisuals.animations;

import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.SpaceUser;

public class Basic2D extends SpaceUser<Euclidean2D> implements SpaceAnimation{

    public Basic2D(){
        super(Euclidean2D.Get());
    }

    @Override
    public void drawAnimation() {
        space.drawSpace();
    }
    
}
