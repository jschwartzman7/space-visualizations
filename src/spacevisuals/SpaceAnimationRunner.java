package spacevisuals;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.spaces.SpaceUser;
import spacevisuals.animations.AnimationsEnum;
import spacevisuals.animations.SpaceAnimation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import edu.princeton.cs.introcs.StdDraw;

/*
 * Object for running animations in a specific space
 */
public class SpaceAnimationRunner{
    
    public final int CANVAS_HEIGHT = 700;
    public final int CANVAS_WIDTH = 700;
    public final int FRAME_RATE;
    public HashSet<AnimationsEnum> multiDimensionalAnimations;
    public static HashMap<Integer, AbstractSpace> spaceKeys;
    public int currentSpaceDimension = 2;

    public SpaceAnimationRunner(int frameRate){
        this.FRAME_RATE = frameRate;
        this.multiDimensionalAnimations = new HashSet<AnimationsEnum>();
        spaceKeys = new HashMap<Integer, AbstractSpace>(){{
            put(2, Euclidean2D.Get());
            put(3, Euclidean3D.Get());
            put(4, Euclidean4D.Get());
        }};
        this.setCanvas();
    }
    
    public AnimationsEnum addAnimation(String animationKey){
        try{
            AnimationsEnum animationEnum = AnimationsEnum.valueOf(animationKey);
            multiDimensionalAnimations.add(animationEnum);
            currentSpaceDimension = animationEnum.dimensions;
            return animationEnum;
        }
        catch(Exception e){
            return null;
        }
    }

    private void setCanvas(){
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.enableDoubleBuffering();
    }

    public void updateAnimation(){
        for(AnimationsEnum animation : multiDimensionalAnimations){
            if(animation.dimensions == currentSpaceDimension){
                animation.animation.updateAnimation();
            }
        }
    };

    public void drawAnimation(){
        for(AnimationsEnum animation : multiDimensionalAnimations){
            if(animation.dimensions == currentSpaceDimension){
                animation.animation.drawAnimation();
            }
        }
    };

    public void run(){
        AbstractSpace currentSpace = spaceKeys.get(currentSpaceDimension);
        while(true){
            StdDraw.clear(currentSpace.colorScheme.backgroundColor);
            currentSpace.updateSpace();
            this.updateAnimation();
            currentSpace.drawSpace();
            this.drawAnimation();
            StdDraw.show(FRAME_RATE);
        }
    }
}
