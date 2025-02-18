package spacevisuals;

import spacevisuals.spaces.AbstractSpace;
import java.util.function.Consumer;
import java.util.function.Function;

public interface SpaceAnimation {
    public default void updateAnimation(){};
    public void drawAnimation();
}
