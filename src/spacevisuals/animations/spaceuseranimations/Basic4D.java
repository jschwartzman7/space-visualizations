package spacevisuals.animations.spaceuseranimations;

import spacevisuals.spaces.Euclidean4D;
import spacevisuals.SpaceUser;
import spacevisuals.animations.SpaceAnimation;

public class Basic4D extends SpaceUser<Euclidean4D> implements SpaceAnimation{

    public Basic4D(){
        super(Euclidean4D.Get());
    }

    @Override
    public void drawAnimation() {
        getSpace().drawSpace();
    }    
}
