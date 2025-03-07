package spacevisuals.animations;
/*
 * Interface for an animation to be drawn on a space
 */
public interface SpaceAnimation {
    public default void updateAnimation(){};
    public void drawAnimation();
    public default void configureAnimation(String[] parameters){};
}
