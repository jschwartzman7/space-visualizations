package spacevisuals;
import spacevisuals.animations.SpaceAnimation;
import spacevisuals.enums.AnimationsEnum;
import spacevisuals.spaces.*;

import java.util.ArrayList;

/*
 * Parses comma separated animation function arguments and runs a built SpaceAnimationRunner
 */
public class Main {
    
    static final char animationSeparator = ',';
    static Euclidean2D euclidean2d = Euclidean2D.Get();
    static Euclidean3D euclidean3d = Euclidean3D.Get(5, 0.01, true);
    static Euclidean4D euclidean4d = Euclidean4D.Get();
    static SpaceAnimationRunner runner = new SpaceAnimationRunner(20);
    static AnimationsEnum[] animationSelections = AnimationsEnum.values();

    public static String[] getUntilSeparator(String[] args, int index){
        ArrayList<String> animationParams = new ArrayList<String>();
        while(index < args.length){
            if(args[index].charAt(0) == animationSeparator){break;}
            animationParams.add(args[index++]);
        }
        return animationParams.toArray(new String[animationParams.size()]);
    }

    public static void handleCommandLineArgs(String[] args){
        if(args.length == 0){
            System.out.println("No animations provided");
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
        runner.run();
    }

    public static void main(String[] args) {
        handleCommandLineArgs(args);
        //handleCommandLineArgs(new String[]{"domaincolor"});
    }
}
