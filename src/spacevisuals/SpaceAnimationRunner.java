package spacevisuals;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.Euclidean2D;
import spacevisuals.spaces.Euclidean3D;
import spacevisuals.spaces.Euclidean4D;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.AnimationsEnum;
import java.util.HashMap;
import edu.princeton.cs.introcs.StdDraw;

/*
 * Object for running animations in a specific space
 */
public class SpaceAnimationRunner{
    
    public final int CANVAS_HEIGHT = 700;
    public final int CANVAS_WIDTH = 700;
    public final int FRAME_RATE;
    public HashMap<SpaceAnimation, Integer> multiDimensionalAnimations;
    public static HashMap<Integer, AbstractSpace> spaceKeys;
    public int currentSpaceDimension = 2;

    public SpaceAnimationRunner(int frameRate){
        this.FRAME_RATE = frameRate;
        this.multiDimensionalAnimations = new HashMap<SpaceAnimation, Integer>();
        spaceKeys = new HashMap<Integer, AbstractSpace>(){{
            put(2, Euclidean2D.Get());
            put(3, Euclidean3D.Get());
            put(4, Euclidean4D.Get());
        }};
        this.setCanvas();
    }
    
    private void setCanvas(){
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.enableDoubleBuffering();
    }

    public SpaceAnimation addAnimation(String animationKey){
        try{
            AnimationsEnum animationEnum = AnimationsEnum.valueOf(animationKey);
            SpaceAnimation animation = animationEnum.animationConstructor.get();
            multiDimensionalAnimations.put(animation, animationEnum.dimensions);
            return animation;
        }
        catch(Exception e){
            return null;
        }
    }

    public void updateAnimation(){
        for(SpaceAnimation animation : multiDimensionalAnimations.keySet()){
            if(multiDimensionalAnimations.get(animation) == currentSpaceDimension){
                animation.updateAnimation();
            }
        }
    };

    public void drawAnimation(){
        for(SpaceAnimation animation : multiDimensionalAnimations.keySet()){
            if(multiDimensionalAnimations.get(animation) == currentSpaceDimension){
                animation.drawAnimation();
            }
        }
    };

    public void run(){
        if(multiDimensionalAnimations.size() == 0){
            System.out.println("No animations provided.");
            return;
        }
        currentSpaceDimension = multiDimensionalAnimations.get(multiDimensionalAnimations.keySet().iterator().next());
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
