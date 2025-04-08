package spacevisuals;

import edu.princeton.cs.introcs.StdDraw;
import spacevisuals.enums.AnimationsEnum;


public class AnimationTests {

    public static boolean testAnimations(){
        for(AnimationsEnum animation : AnimationsEnum.values()){
            if(!testAnimation(animation)){
                System.out.println("Failed testing " + animation.toString());
                return false;
            }
        }
        return true;
    }
    public static boolean testConfigurations(ConfigurableAnimation animation){
        try{
            animation.configureAnimation(new String[0]);
        }catch (Exception e){
            System.out.println("Failed to configure " + animation.toString() + " with an empy String array");
            e.printStackTrace();
            return false;
        }
        try{
            animation.configureAnimation(new String[1]);
        }
        catch (Exception e){
            System.out.println("Failed to configure " + animation.toString() + " with a String array of size 1");
            e.printStackTrace();
            return false;
        }
        try{
            animation.configureAnimation(new String[]{"hyperbolicparabaloid"});
        }
        catch (Exception e){
            System.out.println("Failed to configure " + animation.toString() + " with hyperbolicparabaloid");
            e.printStackTrace();
            return false;
        }
        try{
            animation.configureAnimation(new String[]{"'y-x' '4*x-y'"});
        }
        catch (Exception e){
            System.out.println("Failed to configure " + animation.toString() + " with 'y-x' '4*x-y'");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean testAnimation(AnimationsEnum animation){
        ConfigurableAnimation animationValue = animation.animationConstructor.get();
        assert animationValue != null : "Animation is null";
        testConfigurations(animationValue);
        try{
            animationValue.updateAnimation();
        }
        catch (Exception e){
            System.out.println("Failed to update " + animation.toString());
            e.printStackTrace();
            return false;
        }
        try{
            animationValue.drawAnimation();
            StdDraw.show(50);
        }
        catch (Exception e){
            System.out.println("Failed to draw " + animation.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        System.out.println(testAnimations());
    }
    
}
