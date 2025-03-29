package spacevisuals.animations.spaceuseranimations;

import spacevisuals.spaces.Euclidean3D;
import spacevisuals.SpaceUser;
import spacevisuals.animations.SpaceAnimation;

public class Basic3D extends SpaceUser<Euclidean3D> implements SpaceAnimation{

    public Basic3D(){
        super(Euclidean3D.Get());
    }

    @Override
    public void drawAnimation() {
        getSpace().drawSpace();
        
    }
}
