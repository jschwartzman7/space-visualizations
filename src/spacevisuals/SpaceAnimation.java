package spacevisuals;

import spacevisuals.spaces.AbstractSpace;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class SpaceAnimation<T extends AbstractSpace> {
    protected T space;
    public SpaceAnimation(T space){
        this.space = space;
    }
    public void updateAnimation(){};
    public abstract void drawAnimation();
}
