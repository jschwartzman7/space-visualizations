package spacevisuals.animations;

import spacevisuals.spaces.Euclidean3D;


public class Euclidean3DAnimation extends BasicAnimation<Euclidean3D> {

    public Euclidean3DAnimation(Euclidean3D space, int frameRate){
        super(space, frameRate);
    }

    public void updateAnimation(){
    }

    public void drawAnimation(){
    }
    
    public static void main(String[] args) {
        new Euclidean3DAnimation(new Euclidean3D(5, 5, true), 25).run();
    }
    
}
