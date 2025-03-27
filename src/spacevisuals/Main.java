package spacevisuals;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.AnimationsEnum;
import spacevisuals.spaces.*;

import java.util.ArrayList;

/*
 * Parses comma separated animation function arguments and runs a built SpaceAnimationRunner
 */
public class Main {
    
    static final char animationSeparator = Constants.ANIMATION_SEPARATOR;
    static Euclidean2D euclidean2d = Euclidean2D.Get();
    static Euclidean3D euclidean3d = Euclidean3D.Get();
    static Euclidean4D euclidean4d = Euclidean4D.Get();
    static SpaceAnimationRunner runner = new SpaceAnimationRunner(Constants.FRAME_RATE);
    static AnimationsEnum[] animationSelections = AnimationsEnum.values();

    private static String[] getUntilSeparator(String[] args, int index){
        ArrayList<String> animationParams = new ArrayList<String>();
        while(index < args.length){
            if(args[index].charAt(0) == animationSeparator){break;}
            animationParams.add(args[index++]);
        }
        return animationParams.toArray(new String[animationParams.size()]);
    }

    public static void handleCommandLineArgs(String[] args){
        if(args.length == 0){
            System.out.println("No arguments provided");
            return;
        }
        int i = 0;
        while(i < args.length){
            SpaceAnimation isAnimation = runner.addAnimation(args[i]);
            if(isAnimation != null){
                String[] animationBuildParams = getUntilSeparator(args, i+1);
                if(animationBuildParams.length > 0){
                    isAnimation.configureAnimation(animationBuildParams);
                    i += animationBuildParams.length;
                }
            }
            i++;
        }
        if(runner.multiDimensionalAnimations.size() == 0){
            System.out.println("No valid animations");
            return;
        }
        runner.run();
    }

    public static void main(String[] args) {
        handleCommandLineArgs(args);
        //handleCommandLineArgs(new String[]{"domaincolor"});
    }
}
