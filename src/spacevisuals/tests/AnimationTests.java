package spacevisuals.tests;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.AnimationsEnum;
import spacevisuals.utils.ConfigurableAnimation;
import spacevisuals.utils.Constants;


public class AnimationTests {

    public static boolean testConfigurations = true;
    public static String[] configurationTests = {
            null,
            "",
            " ",
            "j",
            "ikl",
            "hyperbolicparabaloid",
            "x^2"
    };
    
    public static boolean testAnimationConfiguration(ConfigurableAnimation animation, String[] configuration){
        try {
            animation.configureAnimation(configuration);
        } catch (Exception e) {
            System.out.println("Failed to configure " + animation.toString() + " with an empty String array");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean testConfigurations(ConfigurableAnimation animation){
        for(String configuration1 : configurationTests){
            for(String configuration2 : configurationTests){
                for(String configuration3 : configurationTests){
                    if(!testAnimationConfiguration(animation, new String[]{configuration1, configuration2, configuration3})){
                        System.out.println("Failed testing " + animation.toString() + " with configuration " + configuration1 + " " + configuration2);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean testUpdateDraw(ConfigurableAnimation animation){
        try{
            animation.updateAnimation();
        }
        catch (Exception e){
            System.out.println("Failed to update " + animation.toString());
            e.printStackTrace();
            return false;
        }
        try{
            animation.drawAnimation();
            StdDraw.show(40);
        }
        catch (Exception e){
            System.out.println("Failed to draw " + animation.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static boolean testAnimation(AnimationsEnum animation){
        ConfigurableAnimation animationValue = animation.animationConstructor.get();
        assert animationValue != null : "Animation is null";
        if(!testUpdateDraw(animationValue)){
            System.out.println("Failed updating or drawing " + animation.toString());
            return false;
        }
        if(testConfigurations){
            testConfigurations(animationValue);
        }
        if(!testUpdateDraw(animationValue)){
            System.out.println("Failed updating or drawing " + animation.toString());
            return false;
        }
        return true;
    }

    public static boolean testAnimations(){
        for(AnimationsEnum animation : AnimationsEnum.values()){
            if(!testAnimation(animation)){
                System.out.println("Failed testing " + animation.toString());
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-Constants.DEFAULT_CLIP_RADIUS, Constants.DEFAULT_CLIP_RADIUS);
        System.out.println(testAnimations());
    }
    
}
