package spacevisuals.animations.otheranimations;

import spacevisuals.animations.*;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser;

public class Basic4D extends SpaceUser<Euclidean4D> implements SpaceAnimation{

    public Basic4D(){
        super(Euclidean4D.Get());
    }

    @Override
    public void drawAnimation() {
        space.drawSpace();
    }

    @Override
    public void buildAnimation(String[] parameters) {
    }
    
}
