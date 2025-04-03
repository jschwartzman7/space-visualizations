package spacevisuals;
/*
 * Interface for a configurable animation to be drawn
 */
public interface SpaceAnimation {
    public default void configureAnimation(String[] parameters){};
    public default void updateAnimation(){};
    public void drawAnimation();
}
