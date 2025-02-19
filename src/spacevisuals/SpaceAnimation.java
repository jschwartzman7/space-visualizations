package spacevisuals;

import spacevisuals.spaces.AbstractSpace;
import java.util.function.Consumer;
import java.util.function.Function;
/*
 * Interface for an animation to be drawn on a space
 */
public interface SpaceAnimation {
    public default void updateAnimation(){};
    public void drawAnimation();
    public void buildAnimation(String[] parameters);
}
