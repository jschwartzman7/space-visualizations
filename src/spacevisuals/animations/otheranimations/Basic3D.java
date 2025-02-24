package spacevisuals.animations.otheranimations;

import spacevisuals.animations.SpaceAnimation;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.SpaceUser;

public class Basic3D extends SpaceUser<Euclidean3D> implements SpaceAnimation{

    public Basic3D(){
        super(Euclidean3D.Get());
    }

    @Override
    public void drawAnimation() {
        space.drawSpace();
    }

    @Override
    public void buildAnimation(String[] parameters) {
    }
    
}
