package spacevisuals;

import java.util.Map;
import java.util.function.Function;

import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.AnimationsEnum;
/*
 * Test the creation of space animations with different arguments
 */
class TestProgram {

    public String[] testFunctionArguments = new String[]{"", " ", "x", "'x'", "1", "'1'", "'2' 'x'"};
    public AnimationsEnum[] animations = AnimationsEnum.values();

    public void runTests(){
        SpaceAnimationRunner testRunner = new SpaceAnimationRunner(20);
        for(AnimationsEnum animation : animations){
            System.out.println("Testing " + animation + "...");
            for(String argument : testFunctionArguments){
                System.out.println("Testing argument: " + argument);
                SpaceAnimation animationReal = animation.animationConstructor.get();
                animationReal.configureAnimation(new String[]{argument});
                testRunner.addAnimation(animation.toString());
            }
        }
    }

    public static void main(String[] args){
        TestProgram test = new TestProgram();
        test.runTests();
    }

}
