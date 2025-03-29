package spacevisuals.animations.spaceuseranimations;

import spacevisuals.spaces.Euclidean2D;
import spacevisuals.SpaceUser;
import spacevisuals.animations.SpaceAnimation;

public class Basic2D extends SpaceUser<Euclidean2D> implements SpaceAnimation{

    public Basic2D(){
        super(Euclidean2D.Get());
    }

    @Override
    public void drawAnimation() {
        getSpace().drawSpace();
    }
    
}
